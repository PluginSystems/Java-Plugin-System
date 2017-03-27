import com.github.ysl3000.pluginsystem.IPlugin;
import com.github.ysl3000.pluginsystem.PluginLoader;
import com.github.ysl3000.pluginsystem.PropertyPluginConfigLoader;
import com.github.ysl3000.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.pluginsystem.interfaces.PluginConfigLoader;
import com.github.ysl3000.plugintest.ConsoleMessageLogger;
import org.junit.Test;

import java.io.File;

/**
 * Created by ysl3000
 */
public class MainTest {

    @Test
    public void testLoader(){

        MessageLogger messageLogger = new ConsoleMessageLogger();
        File folder = new File("plugins");
        folder.mkdirs();

        PluginConfigLoader pluginConfigLoader = new PropertyPluginConfigLoader();

        PluginLoader<IPlugin> pluginLoader = new PluginLoader<>(messageLogger,folder,pluginConfigLoader);

        pluginLoader.load();

        pluginLoader.enable();

        pluginLoader.disable();

    }

}
