public class MessageTest
{
  /**
   * Simulates a conversation between three people
   */
  public static void main(String[] args)
  {
    Person person1 = new Person.PersonBuilder(
      System.out, "Alice").build();
    Person person2 = new Person.PersonBuilder(
      System.out, "Bob").build();
    Person person3 = new Person.PersonBuilder(
      System.out, "Carlos").build();
    printState(person1, person2, person3);

    person1.sendMessage(person2, "Hello");
    printState(person1, person2, person3);

    person2.sendMessage(person1, "Hi there!");
    printState(person1, person2, person3);
    
    person1.sendMessage(person2, "How are you?");
    printState(person1, person2, person3);
    
    person2.sendMessage(person1, 
      String.format("I'm good. Hey, there's %s!", person3.getName()));
    printState(person1, person2, person3);
    
    person1.sendMessage(person3,
      String.format("Hey %s!", person3.getName()));
    printState(person1, person2, person3);
    
    person2.sendMessage(person3,
      String.format("Holla %s!", person3.getName()));
    printState(person1, person2, person3);
  }

  /**
   * Prints out how many conversation events each person in the arguments
   * thinks has happened.
   */
  private static void printState(Person... people)
  {
    for(Person person : people)
    {
      System.out.printf("%s: %s\n", person.getName(),
        person.getTotalEvents()); 
    }
  }
}