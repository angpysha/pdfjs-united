using Foundation;
using MobileCoreServices;
using System;
using System.Linq;
using UIKit;

namespace PDFJSViewer.Demo
{
    public partial class ViewController : UIViewController, IUIDocumentPickerDelegate
    {
        public ViewController (IntPtr handle) : base (handle)
        {
        }

        public override void ViewDidLayoutSubviews()
        {
            base.ViewDidLayoutSubviews();

        }

        public override void ViewDidLoad ()
        {
            base.ViewDidLoad ();
            // Perform any additional setup after loading the view, typically from a nib.
        }

        public override void DidReceiveMemoryWarning ()
        {
            base.DidReceiveMemoryWarning ();
            // Release any cached data, images, etc that aren't in use.
        }

        public void DidPickDocument(UIDocumentPickerViewController controller, NSUrl url)
        {
            
        }

        [Export("documentPicker:didPickDocumentsAtURLs:")]
        public void DidPickDocument(UIDocumentPickerViewController controller, NSUrl[] urls)
        {

            var url = urls.First();
            OutletPdfJsView.Source = url;
        }

        partial void button_presed(NSObject sender)
        {
            // var controller = new UIDocumentPickerViewController(new[] { UTType.PDF.ToString() }, UIDocumentPickerMode.Import);
            var controller = new UIDocumentPickerViewController(new UniformTypeIdentifiers.UTType[] { UniformTypeIdentifiers.UTTypes.Pdf }, true);
            controller.Delegate = this;
            
            this.PresentViewController(controller, false, null);
        }
    }
}
