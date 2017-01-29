import Foundation

@objc(MyLogger)
class MyLogger: NSObject {
  
  @objc(callFunc:)
  func callFunc(typeParam: NSInteger) {
    print("typeParam");
    print(typeParam);
    
    let uiViewController = UIViewController()
    uiViewController.view.backgroundColor = UIColor.gray;
    let appDelegate:AppDelegate = UIApplication.shared.delegate as! AppDelegate
    let rootViewController:UIViewController = appDelegate.rootViewController!
    rootViewController.present(uiViewController, animated: true, completion: nil)
  }
}
