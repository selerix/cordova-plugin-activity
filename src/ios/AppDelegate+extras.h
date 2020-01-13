#import "AppDelegate.h"
@import UserNotifications;

@interface AppDelegate (extras) <UNUserNotificationCenterDelegate>
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:( void (^)(UIBackgroundFetchResult))completionHandler;
@end
