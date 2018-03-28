package co.bytemark.models.resetpin;
public class ResetPinRootResponseModel
{
    private String[] errors;

    private ResetPinResponseModelData data;

    private String server_time;

    public String[] getErrors ()
    {
        return errors;
    }

    public void setErrors (String[] errors)
    {
        this.errors = errors;
    }

    public ResetPinResponseModelData getData ()
    {
        return data;
    }

    public void setData (ResetPinResponseModelData data)
    {
        this.data = data;
    }

    public String getServer_time ()
    {
        return server_time;
    }

    public void setServer_time (String server_time)
    {
        this.server_time = server_time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errors = "+errors+", data = "+data+", server_time = "+server_time+"]";
    }
}
			
			