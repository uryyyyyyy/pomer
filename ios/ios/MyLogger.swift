import Foundation
import MediaPlayer

@objc(MyLogger)
class MyLogger: NSObject {
  
  @objc(callFunc:)
  func callFunc(typeParam: NSInteger) {
    print("typeParam");
    print(typeParam);
  }
}
