import com.github.ysl3000.twitchapi.TwitchAPI;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ysl3000
 */
public class TwitchAPITest {

    @Test
    public void testTwitchAPI(){

        TwitchAPI twitchAPI = new TwitchAPI("iiwsu6n5r8qiu1cug6zwupldjkfyn3");

        String name = "deadpine";

        try {
            System.out.println("TwitchStreamer "+name+" isOnline: " + twitchAPI
                    .getSyncTwitchConnector().isStreamingOnline(name));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

}
