import UIKit
import Firebase
import GoogleSignIn

@objc(AppDelegate)
class AppDelegate: UIResponder, UIApplicationDelegate, GIDSignInDelegate {
  
  var window: UIWindow?
  var rootViewController: UIViewController?
  
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
    self.window = UIWindow.init(frame: UIScreen.main.bounds)
    FIRApp.configure()
    GIDSignIn.sharedInstance().clientID = FIRApp.defaultApp()?.options.clientID
    GIDSignIn.sharedInstance().delegate = self
    
    
    //React Native View
    let settings = RCTBundleURLProvider.sharedSettings()
    let jsCodeLocation: URL = settings!.jsBundleURL(forBundleRoot: "index.ios", fallbackResource: nil)
    let rootView = RCTRootView.init(bundleURL: jsCodeLocation as URL!, moduleName: "pomer", initialProperties: nil, launchOptions: launchOptions)
    rootView?.backgroundColor = UIColor.init(red: 1.0, green: 1.0, blue: 1.0, alpha: 1)
    self.window = UIWindow.init(frame: UIScreen.main.bounds)
    self.rootViewController = UIViewController()
    self.rootViewController?.view = rootView;
    self.window?.rootViewController = self.rootViewController;
    self.window?.makeKeyAndVisible()
    return true
  }
  
  
  func applicationWillResignActive(_ application: UIApplication) {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    
  }
  
  
  func applicationDidEnterBackground(_ application: UIApplication) {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    
  }
  
  
  func applicationWillEnterForeground(_ application: UIApplication) {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
  }
  
  
  func applicationDidBecomeActive(_ application: UIApplication) {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
  }
  
  
  func applicationWillTerminate(_ application: UIApplication) {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
  }
  
  
  @available(iOS 9.0, *)
  func application(_ application: UIApplication, open url: URL, options: [UIApplicationOpenURLOptionsKey: Any]) -> Bool {
    return GIDSignIn.sharedInstance().handle(url,
                                             sourceApplication: options[UIApplicationOpenURLOptionsKey.sourceApplication] as? String,
                                             annotation: [:])
  }
  
  func sign(_ signIn: GIDSignIn!, didSignInFor user: GIDGoogleUser!, withError error: Error?) {
    if let error = error {
      print("%@", error);
      return
    }
    
    guard let authentication = user.authentication else { return }
    let credential = FIRGoogleAuthProvider.credential(withIDToken: authentication.idToken,
                                                      accessToken: authentication.accessToken)
    print("credential")
    
    FIRAuth.auth()?.signIn(with: credential) { (user, error2) in
      if let error2 = error2 {
        print("%@", error2);
        return
      }
      print("user email: " + (user?.email)!)
      self.rootViewController?.dismiss(animated: true, completion: nil)
    }
  }
  
  func signIn(signIn: GIDSignIn!, didDisconnectWithUser user:GIDGoogleUser!,
              withError error: NSError!) {
    if let error = error {
      print("%@", error);
      return
    }
  }
  
}
