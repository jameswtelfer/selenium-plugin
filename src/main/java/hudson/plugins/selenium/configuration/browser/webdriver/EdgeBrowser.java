package hudson.plugins.selenium.configuration.browser.webdriver;

import hudson.Extension;
import hudson.util.FormValidation;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.util.HashMap;
import java.util.Map;

public class EdgeBrowser extends DriverRequiredWebDriverBrowser {
    /**
     * @param maxInstances        Number of instances to run of this browser type session.
     * @param version          Version of the browser to use.
     * @param driverBinaryPath
     */
    @DataBoundConstructor
    public EdgeBrowser(int maxInstances, String version, String driverBinaryPath) {
        super(maxInstances, version, "edge", driverBinaryPath);
    }

    @Override
    public Map<String, String> getJVMArgs() {
        Map<String, String> args = new HashMap<String, String>();
        combine(args, edgeDriverProperty, getDriverBinaryPath());
        return args;
    }

    @Extension
    public static class DescriptorImpl extends WebDriverBrowserDescriptor {

        public int getMaxInstances() {
            return 1;
        }

        @Override
        public String getDisplayName() {
            return "Edge";
        }

        public FormValidation doCheckDriverBinaryPath(@QueryParameter String value) {
            if (StringUtils.isBlank(value)) {
                return FormValidation
                        .warning("Must not be empty");
            }
            return FormValidation.ok();
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * System property to specify the edge binary location.
     */
    private transient final String edgeDriverProperty = "webdriver.edge.driver";

}
