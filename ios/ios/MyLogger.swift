import Foundation

@objc(MyLogger)
class MyLogger: NSObject {
  
  @objc(callFunc:)
  func callFunc(typeParam: NSInteger) {
    print("typeParam");
    print(typeParam);
    
    FIRAnalytics.setUserPropertyString("favorite", forName: "curry")
    
    FIRAnalytics.logEvent(withName: kFIREventSelectContent, parameters: [
      kFIRParameterContentType:"count" as NSObject,
      kFIRParameterItemID:"1" as NSObject
      ])
  }
}
