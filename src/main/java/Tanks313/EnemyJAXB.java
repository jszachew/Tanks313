package Tanks313;
import javax.xml.bind.*;
import java.io.*;

public class EnemyJAXB {
    private Controller c;
    private Textures tex;
    private App game;

    public void marshall()
    {
        try
        {
            Enemy enemy = new Enemy(0,0,tex,c,game);
            JAXBContext jc=JAXBContext.newInstance(Enemy.class);
            Marshaller ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            ms.marshal(enemy, new File("src\\data\\Enemy.xml"));
        }
        catch (Exception e)
        {

        }
    }

    public void unmarshall()
    {
        try
        {
            JAXBContext jc =JAXBContext.newInstance(Enemy.class);
            Unmarshaller ums=jc.createUnmarshaller();
            Enemy enemy = (Enemy) ums.unmarshal(new File("src\\data\\Enemy.xml"));
        }
        catch (Exception e)
        {

        }
    }
}
