import java.util.*;

public class FeatureFlagSystem {

    // Feature class
    static class Feature {
        private String name;

        public Feature(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Flag class
    static class Flag {
        private boolean globallyEnabled;
        private Set<String> usersEnabled;
        private Set<String> usersDisabled;

        public Flag(boolean globallyEnabled) {
            this.globallyEnabled = globallyEnabled;
            this.usersEnabled = new HashSet<>();
            this.usersDisabled = new HashSet<>();
        }

        public void setGloballyEnabled(boolean status) {
            this.globallyEnabled = status;
        }

        public void enableForUser(String userId) {
            usersEnabled.add(userId);
            usersDisabled.remove(userId);
        }

        public void disableForUser(String userId) {
            usersDisabled.add(userId);
            usersEnabled.remove(userId);
        }

        public boolean isEnabledForUser(String userId) {
            if (usersEnabled.contains(userId)) return true;
            if (usersDisabled.contains(userId)) return false;
            return globallyEnabled;
        }
    }

    // FeatureFlagService class
    static class FeatureFlagService {
        private Map<String, Flag> featureFlags;

        public FeatureFlagService() {
            this.featureFlags = new HashMap<>();
        }

        public void createFeature(String featureName, boolean globallyEnabled) {
            featureFlags.put(featureName, new Flag(globallyEnabled));
        }

        public void setGlobalFlag(String featureName, boolean status) {
            if (featureFlags.containsKey(featureName)) {
                featureFlags.get(featureName).setGloballyEnabled(status);
            }
        }

        public void enableFeatureForUser(String featureName, String userId) {
            if (featureFlags.containsKey(featureName)) {
                featureFlags.get(featureName).enableForUser(userId);
            }
        }

        public void disableFeatureForUser(String featureName, String userId) {
            if (featureFlags.containsKey(featureName)) {
                featureFlags.get(featureName).disableForUser(userId);
            }
        }

        public boolean isFeatureAvailable(String userId, String featureName) {
            if (!featureFlags.containsKey(featureName)) return false;
            return featureFlags.get(featureName).isEnabledForUser(userId);
        }
    }

    // Demo main method
    public static void main(String[] args) {
        FeatureFlagService flagService = new FeatureFlagService();

        flagService.createFeature("dark_mode", false);
        flagService.enableFeatureForUser("dark_mode", "user123");

        System.out.println(flagService.isFeatureAvailable("user123", "dark_mode")); // true
        System.out.println(flagService.isFeatureAvailable("user456", "dark_mode")); // false

        flagService.setGlobalFlag("dark_mode", true);
        System.out.println(flagService.isFeatureAvailable("user456", "dark_mode")); // true
    }
}
