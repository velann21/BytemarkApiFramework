package co.bytemark.models.resetpin;
public class ResetPinResponseModelData
{
    private String status;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+"]";
    }
}
	