/**
 * Provides a simple implementation of a LamportClock
 * PayloadType is the type of Message that the instance of LamportClock will
 * send and receive.
 */
public abstract class LamportClock<PayloadType>
{
  protected long logicalTime;

  protected LamportClock(LamportClockBuilder builder)
  {
    logicalTime = builder.initialLogicalTime;
  }

  /**
   * To be called whenever an event occurs
   */
  protected void tick()
  {
    logicalTime++;
  }

  /**
   * Recieve a message from another LamportClock
   */
  protected void receiveMessage(Message<PayloadType> msg)
  {
    if(logicalTime < (msg.getTimestamp() + 1))
    {
      logicalTime = (msg.getTimestamp() + 1);
    }
  }

  /**
   * Send a message to another LamportClock
   * @param receiver The LamportClock to send the message to
   * @param payload The payload of the message
   */
  protected void sendMessage(LamportClock<PayloadType> receiver,
    PayloadType payload)
  {
    tick();
    Message<PayloadType> msg = new Message<PayloadType>(payload,
      logicalTime, this);
    receiver.receiveMessage(msg);
  }

  /**
   * A builder class for the Lamport Clock
   */
  public static abstract class LamportClockBuilder
    <BuilderType extends LamportClockBuilder<BuilderType>>
  {
    private long initialLogicalTime;

    public LamportClockBuilder()
    {
      // Set the default values
      initialLogicalTime = 0;
    }

    protected abstract BuilderType self();

    /**
     * Builds a new instance of a Lamport Clock
     * @return A new LamportClock with the parameters set in this builder
     */
    public abstract LamportClock build();

    /**
     * Set the start time of the clock
     */
    public BuilderType initialTime(long initialTime)
    {
      initialLogicalTime = initialTime;
      return self();
    }
  }

  /**
   * A wrapper class around a payload object 
   */
  public static class Message<PayloadType>
  {
    private PayloadType payload;
    private long logicalTime;
    private LamportClock sender;

    private Message(PayloadType payload, long logicalTime,
      LamportClock sender)
    {
      this.payload = payload;
      this.logicalTime = logicalTime;
      this.sender = sender;
    }

    public PayloadType getPayload() { return payload; }
    public long getTimestamp() { return logicalTime; }
    public LamportClock getSender() { return sender; }
  }
}