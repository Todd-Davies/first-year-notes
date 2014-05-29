import java.io.PrintStream;

/**
 * A sample implementation of a Lamport Clock. Each instance of the Person class
 * keeps track of how many conversation events occured between the people (so in
 * one exchange, there is one 'say' event and one 'listen' event), and in line
 * with how a Lamport Clock works, this count is passed on from the sending
 * person to the receiving person on each sent message.
 *
 * We assume that if Alice says something to Bob, then only Bob hears, and some
 * third party (say Carlos), never knows anything was said.
 */
public class Person extends LamportClock<Person.MessagePair>
{
  // The outputStream is like a person's ears. It's what they hear.
  private PrintStream outputStream;
  private String name;

  public Person(PersonBuilder builder)
  {
    super(builder);
    outputStream = builder.output;
    name = builder.name;
  }

  /**
   * Called when a person hears something said to them
   * @param msg The Message (an instance of MessagePair) to be passed to the
   *            Person
   */
  @Override
  protected void receiveMessage(Message<Person.MessagePair> msg)
  {
    super.receiveMessage(msg);
    outputStream.printf("%s -> %s: %s\n", msg.getPayload().person.getName(),
      name, msg.getPayload().message);
  }

  /**
   * Makes this person say something to another person
   */
  public void sendMessage(LamportClock<Person.MessagePair> receivingPerson,
    String message)
  {
    super.sendMessage(receivingPerson, new MessagePair(this, message));
  }

  public String getName() { return name; }

  public long getTotalEvents() { return logicalTime; }

  // The superclass dictates that we have to use a builder
  public static class PersonBuilder extends
    LamportClock.LamportClockBuilder<PersonBuilder>
  {
    private PrintStream output;
    private String name;

    public PersonBuilder(PrintStream outputStream, String name)
    {
      output = outputStream;
      this.name = name;
    }

    public Person build()
    {
      return new Person(this);
    }

    @Override
    protected PersonBuilder self()
    {
      return this;
    }
  }

  /**
   * A helper class to wrap the message and the sending person into one object
   */
  public static final class MessagePair
  {
    public final Person person;
    public final String message;

    public MessagePair(Person person, String message)
    {
      this.person = person;
      this.message = message;
    }
  }
}