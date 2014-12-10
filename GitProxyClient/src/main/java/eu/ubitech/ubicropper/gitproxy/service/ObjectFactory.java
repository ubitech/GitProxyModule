
package eu.ubitech.ubicropper.gitproxy.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.ubitech.ubicropper.gitproxy.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllRevisionsResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getAllRevisionsResponse");
    private final static QName _GetLatestRevisionResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getLatestRevisionResponse");
    private final static QName _GetLatestRevision_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getLatestRevision");
    private final static QName _SaveImageToCERIFBook_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "saveImageToCERIFBook");
    private final static QName _InitializeSIF4Province_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "initializeSIF4Province");
    private final static QName _EchoResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "echoResponse");
    private final static QName _GetImage_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getImage");
    private final static QName _SaveImageToCERIFBookResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "saveImageToCERIFBookResponse");
    private final static QName _ManageTmpFolder_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "manageTmpFolder");
    private final static QName _SaveImage_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "saveImage");
    private final static QName _GetFilesBetweenTwoCommits_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesBetweenTwoCommits");
    private final static QName _GetAllRevisions_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getAllRevisions");
    private final static QName _InitializeSIF4ProvinceResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "initializeSIF4ProvinceResponse");
    private final static QName _DeleteImageResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "deleteImageResponse");
    private final static QName _GetFilesBetweenTwoCommitsResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesBetweenTwoCommitsResponse");
    private final static QName _GetFilesOfCommit_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesOfCommit");
    private final static QName _GetImageResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getImageResponse");
    private final static QName _SaveImageResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "saveImageResponse");
    private final static QName _DeleteImage_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "deleteImage");
    private final static QName _Echo_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "echo");
    private final static QName _ManageTmpFolderResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "manageTmpFolderResponse");
    private final static QName _GetFilesOfCommitResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesOfCommitResponse");
    private final static QName _SaveImageArg1_QNAME = new QName("", "arg1");
    private final static QName _SaveImageToCERIFBookArg2_QNAME = new QName("", "arg2");
    private final static QName _GetImageResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.ubitech.ubicropper.gitproxy.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InitializeSIF4Province }
     * 
     */
    public InitializeSIF4Province createInitializeSIF4Province() {
        return new InitializeSIF4Province();
    }

    /**
     * Create an instance of {@link SaveImageToCERIFBookResponse }
     * 
     */
    public SaveImageToCERIFBookResponse createSaveImageToCERIFBookResponse() {
        return new SaveImageToCERIFBookResponse();
    }

    /**
     * Create an instance of {@link GetImage }
     * 
     */
    public GetImage createGetImage() {
        return new GetImage();
    }

    /**
     * Create an instance of {@link GetAllRevisionsResponse }
     * 
     */
    public GetAllRevisionsResponse createGetAllRevisionsResponse() {
        return new GetAllRevisionsResponse();
    }

    /**
     * Create an instance of {@link SaveImageResponse }
     * 
     */
    public SaveImageResponse createSaveImageResponse() {
        return new SaveImageResponse();
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link SaveImage }
     * 
     */
    public SaveImage createSaveImage() {
        return new SaveImage();
    }

    /**
     * Create an instance of {@link GetLatestRevision }
     * 
     */
    public GetLatestRevision createGetLatestRevision() {
        return new GetLatestRevision();
    }

    /**
     * Create an instance of {@link DeleteImage }
     * 
     */
    public DeleteImage createDeleteImage() {
        return new DeleteImage();
    }

    /**
     * Create an instance of {@link GetFilesBetweenTwoCommits }
     * 
     */
    public GetFilesBetweenTwoCommits createGetFilesBetweenTwoCommits() {
        return new GetFilesBetweenTwoCommits();
    }

    /**
     * Create an instance of {@link GetFilesOfCommitResponse }
     * 
     */
    public GetFilesOfCommitResponse createGetFilesOfCommitResponse() {
        return new GetFilesOfCommitResponse();
    }

    /**
     * Create an instance of {@link DeleteImageResponse }
     * 
     */
    public DeleteImageResponse createDeleteImageResponse() {
        return new DeleteImageResponse();
    }

    /**
     * Create an instance of {@link GetFilesBetweenTwoCommitsResponse }
     * 
     */
    public GetFilesBetweenTwoCommitsResponse createGetFilesBetweenTwoCommitsResponse() {
        return new GetFilesBetweenTwoCommitsResponse();
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link GetAllRevisions }
     * 
     */
    public GetAllRevisions createGetAllRevisions() {
        return new GetAllRevisions();
    }

    /**
     * Create an instance of {@link InitializeSIF4ProvinceResponse }
     * 
     */
    public InitializeSIF4ProvinceResponse createInitializeSIF4ProvinceResponse() {
        return new InitializeSIF4ProvinceResponse();
    }

    /**
     * Create an instance of {@link ManageTmpFolderResponse }
     * 
     */
    public ManageTmpFolderResponse createManageTmpFolderResponse() {
        return new ManageTmpFolderResponse();
    }

    /**
     * Create an instance of {@link SaveImageToCERIFBook }
     * 
     */
    public SaveImageToCERIFBook createSaveImageToCERIFBook() {
        return new SaveImageToCERIFBook();
    }

    /**
     * Create an instance of {@link GetLatestRevisionResponse }
     * 
     */
    public GetLatestRevisionResponse createGetLatestRevisionResponse() {
        return new GetLatestRevisionResponse();
    }

    /**
     * Create an instance of {@link GetImageResponse }
     * 
     */
    public GetImageResponse createGetImageResponse() {
        return new GetImageResponse();
    }

    /**
     * Create an instance of {@link GetFilesOfCommit }
     * 
     */
    public GetFilesOfCommit createGetFilesOfCommit() {
        return new GetFilesOfCommit();
    }

    /**
     * Create an instance of {@link ManageTmpFolder }
     * 
     */
    public ManageTmpFolder createManageTmpFolder() {
        return new ManageTmpFolder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRevisionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getAllRevisionsResponse")
    public JAXBElement<GetAllRevisionsResponse> createGetAllRevisionsResponse(GetAllRevisionsResponse value) {
        return new JAXBElement<GetAllRevisionsResponse>(_GetAllRevisionsResponse_QNAME, GetAllRevisionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLatestRevisionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getLatestRevisionResponse")
    public JAXBElement<GetLatestRevisionResponse> createGetLatestRevisionResponse(GetLatestRevisionResponse value) {
        return new JAXBElement<GetLatestRevisionResponse>(_GetLatestRevisionResponse_QNAME, GetLatestRevisionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLatestRevision }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getLatestRevision")
    public JAXBElement<GetLatestRevision> createGetLatestRevision(GetLatestRevision value) {
        return new JAXBElement<GetLatestRevision>(_GetLatestRevision_QNAME, GetLatestRevision.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveImageToCERIFBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "saveImageToCERIFBook")
    public JAXBElement<SaveImageToCERIFBook> createSaveImageToCERIFBook(SaveImageToCERIFBook value) {
        return new JAXBElement<SaveImageToCERIFBook>(_SaveImageToCERIFBook_QNAME, SaveImageToCERIFBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitializeSIF4Province }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "initializeSIF4Province")
    public JAXBElement<InitializeSIF4Province> createInitializeSIF4Province(InitializeSIF4Province value) {
        return new JAXBElement<InitializeSIF4Province>(_InitializeSIF4Province_QNAME, InitializeSIF4Province.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "echoResponse")
    public JAXBElement<EchoResponse> createEchoResponse(EchoResponse value) {
        return new JAXBElement<EchoResponse>(_EchoResponse_QNAME, EchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getImage")
    public JAXBElement<GetImage> createGetImage(GetImage value) {
        return new JAXBElement<GetImage>(_GetImage_QNAME, GetImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveImageToCERIFBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "saveImageToCERIFBookResponse")
    public JAXBElement<SaveImageToCERIFBookResponse> createSaveImageToCERIFBookResponse(SaveImageToCERIFBookResponse value) {
        return new JAXBElement<SaveImageToCERIFBookResponse>(_SaveImageToCERIFBookResponse_QNAME, SaveImageToCERIFBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManageTmpFolder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "manageTmpFolder")
    public JAXBElement<ManageTmpFolder> createManageTmpFolder(ManageTmpFolder value) {
        return new JAXBElement<ManageTmpFolder>(_ManageTmpFolder_QNAME, ManageTmpFolder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "saveImage")
    public JAXBElement<SaveImage> createSaveImage(SaveImage value) {
        return new JAXBElement<SaveImage>(_SaveImage_QNAME, SaveImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesBetweenTwoCommits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesBetweenTwoCommits")
    public JAXBElement<GetFilesBetweenTwoCommits> createGetFilesBetweenTwoCommits(GetFilesBetweenTwoCommits value) {
        return new JAXBElement<GetFilesBetweenTwoCommits>(_GetFilesBetweenTwoCommits_QNAME, GetFilesBetweenTwoCommits.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRevisions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getAllRevisions")
    public JAXBElement<GetAllRevisions> createGetAllRevisions(GetAllRevisions value) {
        return new JAXBElement<GetAllRevisions>(_GetAllRevisions_QNAME, GetAllRevisions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitializeSIF4ProvinceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "initializeSIF4ProvinceResponse")
    public JAXBElement<InitializeSIF4ProvinceResponse> createInitializeSIF4ProvinceResponse(InitializeSIF4ProvinceResponse value) {
        return new JAXBElement<InitializeSIF4ProvinceResponse>(_InitializeSIF4ProvinceResponse_QNAME, InitializeSIF4ProvinceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "deleteImageResponse")
    public JAXBElement<DeleteImageResponse> createDeleteImageResponse(DeleteImageResponse value) {
        return new JAXBElement<DeleteImageResponse>(_DeleteImageResponse_QNAME, DeleteImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesBetweenTwoCommitsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesBetweenTwoCommitsResponse")
    public JAXBElement<GetFilesBetweenTwoCommitsResponse> createGetFilesBetweenTwoCommitsResponse(GetFilesBetweenTwoCommitsResponse value) {
        return new JAXBElement<GetFilesBetweenTwoCommitsResponse>(_GetFilesBetweenTwoCommitsResponse_QNAME, GetFilesBetweenTwoCommitsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesOfCommit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesOfCommit")
    public JAXBElement<GetFilesOfCommit> createGetFilesOfCommit(GetFilesOfCommit value) {
        return new JAXBElement<GetFilesOfCommit>(_GetFilesOfCommit_QNAME, GetFilesOfCommit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getImageResponse")
    public JAXBElement<GetImageResponse> createGetImageResponse(GetImageResponse value) {
        return new JAXBElement<GetImageResponse>(_GetImageResponse_QNAME, GetImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "saveImageResponse")
    public JAXBElement<SaveImageResponse> createSaveImageResponse(SaveImageResponse value) {
        return new JAXBElement<SaveImageResponse>(_SaveImageResponse_QNAME, SaveImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "deleteImage")
    public JAXBElement<DeleteImage> createDeleteImage(DeleteImage value) {
        return new JAXBElement<DeleteImage>(_DeleteImage_QNAME, DeleteImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Echo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "echo")
    public JAXBElement<Echo> createEcho(Echo value) {
        return new JAXBElement<Echo>(_Echo_QNAME, Echo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManageTmpFolderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "manageTmpFolderResponse")
    public JAXBElement<ManageTmpFolderResponse> createManageTmpFolderResponse(ManageTmpFolderResponse value) {
        return new JAXBElement<ManageTmpFolderResponse>(_ManageTmpFolderResponse_QNAME, ManageTmpFolderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesOfCommitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesOfCommitResponse")
    public JAXBElement<GetFilesOfCommitResponse> createGetFilesOfCommitResponse(GetFilesOfCommitResponse value) {
        return new JAXBElement<GetFilesOfCommitResponse>(_GetFilesOfCommitResponse_QNAME, GetFilesOfCommitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg1", scope = SaveImage.class)
    public JAXBElement<byte[]> createSaveImageArg1(byte[] value) {
        return new JAXBElement<byte[]>(_SaveImageArg1_QNAME, byte[].class, SaveImage.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg2", scope = SaveImageToCERIFBook.class)
    public JAXBElement<byte[]> createSaveImageToCERIFBookArg2(byte[] value) {
        return new JAXBElement<byte[]>(_SaveImageToCERIFBookArg2_QNAME, byte[].class, SaveImageToCERIFBook.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetImageResponse.class)
    public JAXBElement<byte[]> createGetImageResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetImageResponseReturn_QNAME, byte[].class, GetImageResponse.class, ((byte[]) value));
    }

}
