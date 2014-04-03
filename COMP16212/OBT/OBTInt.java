public class OBTInt
{

  private int value;
  private OBTInt left;
  private OBTInt right;

  /**
   * @param value The value of the node
   * @throws IllegalArgumentException If the value is not a natural number
   */
  public OBTInt(int value) throws IllegalArgumentException
  {
    this.setValue(value);
  }

  public OBTInt()
  {
    this.setEmpty();
  }

  /**
   * Is the tree empty
   * @return true if the node is empty, false if otherwise
   */
  public boolean isEmpty()
  {
    return this.value == -1;
  }

  /**
   * Gets the value of the node
   * @returns The value of the node, -1 if it is an empty node
   */
  public int getValue()
  {
    return this.value;
  }

  /**
   * Gets the left sub tree
   */
  public OBTInt getLeft()
  {
    return this.left;
  }

  /**
   * Gets the right sub tree
   */
  public OBTInt getRight()
  {
    return this.right;
  }

  /**
   * Set the value of the node
   * @param value The new value of the node
   * @throws IllegalArgumentException If the value is not a natural number
   */
  public void setValue(int value) throws IllegalArgumentException
  {
    if(value<0)
    {
      throw new IllegalArgumentException("The value cannot be less than 0");
    }
    this.left = new OBTInt();
    this.right = new OBTInt();
    this.value = value;
  }

  /**
   * Make the node empty
   */
  public void setEmpty()
  {
    this.left = null;
    this.right = null;
    // Since the tree can only store natural numbers, -1 indicates an empty node
    this.value = -1;
  }

  /**
   * Set the value of a child node
   * @param value The new value of the node
   * @throws IllegalArgumentException If the value is not a natural number
   */
  public void insert(int value) throws IllegalArgumentException
  {
    if(this.isEmpty())
    {
      this.setValue(value);
    }
    else if(this.getValue()<value)
    {
      this.getLeft().insert(value);
    } else {
      this.getRight().insert(value);
    }
  }

  /**
   * Searches the tree for a value.
   * @param value The value to look for in the tree
   * @return true if the value is in the tree, false if otherwise.
   */
  public boolean find(int value)
  {
    if(this.isEmpty())
    {
      return false;
    }
    else if(this.getValue()==value)
    {
      return true;
    }
    else if(this.getValue()<value)
    {
      return this.getLeft().find(value);
    }
    else
    {
      return this.getRight().find(value);
    }
  }
}