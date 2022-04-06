public class Car
{

    String type;
  
    public Car(String type)
    {
        this.type = type;     
    }
 
    public String getType()
    {
        return this.type;
    }
 
    @Override
    public String toString()
    {
        return("Car type: " + this.getType());
    }
  
}