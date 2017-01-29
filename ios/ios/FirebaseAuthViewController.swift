import UIKit
import Firebase
import GoogleSignIn

class FirebaseAuthViewController: UIViewController, GIDSignInUIDelegate {
  
  var credential: FIRAuthCredential? = nil
  
  override func viewDidLoad() {
    super.viewDidLoad()
    self.view.backgroundColor = UIColor.cyan
    
    let button = UIButton(frame: CGRect(x: 100, y: 100, width: 200, height: 50))
    button.backgroundColor = .gray
    button.setTitle("Google SignIn", for: .normal)
    button.addTarget(self, action: #selector(buttonAction), for: .touchUpInside)
    
    self.view.addSubview(button)
  }
  
  func buttonAction(sender: UIButton!) {
    print("Button tapped")
    GIDSignIn.sharedInstance().uiDelegate = self
    GIDSignIn.sharedInstance().signIn()
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  
  
}
