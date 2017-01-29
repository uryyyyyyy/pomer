import Foundation

@objc(FirebaseAuth)
class FirebaseAuth: NSObject {
  
  @objc(openView)
  func openView() {
    print("openView");
    
    let uiViewController = FirebaseAuthViewController()
    let appDelegate:AppDelegate = UIApplication.shared.delegate as! AppDelegate
    let rootViewController:UIViewController = appDelegate.rootViewController
    rootViewController.present(uiViewController, animated: true, completion: nil)
  }
}
