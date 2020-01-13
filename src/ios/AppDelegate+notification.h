#import "AppDelegate.h"
@import UserNotifications;

@interface AppDelegate (notification) <UNUserNotificationCenterDelegate>
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:( void (^)(UIBackgroundFetchResult))completionHandler;
@end
