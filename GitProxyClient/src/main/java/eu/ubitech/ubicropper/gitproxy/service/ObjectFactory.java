
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
    private final static QName _GetFilesBetweenTwoCommitsResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesBetweenTwoCommitsResponse");
    private final static QName _GetLatestRevision_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getLatestRevision");
    private final static QName _GetFilesOfCommit_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesOfCommit");
    private final static QName _Echo_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "echo");
    private final static QName _EchoResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "echoResponse");
    private final static QName _GetFilesBetweenTwoCommits_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesBetweenTwoCommits");
    private final static QName _GetAllRevisions_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getAllRevisions");
    private final static QName _GetFilesOfCommitResponse_QNAME = new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "getFilesOfCommitResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.ubitech.ubicropper.gitproxy.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link GetLatestRevisionResponse }
     * 
     */
    public GetLatestRevisionResponse createGetLatestRevisionResponse() {
        return new GetLatestRevisionResponse();
    }

    /**
     * Create an instance of {@link GetFilesBetweenTwoCommits }
     * 
     */
    public GetFilesBetweenTwoCommits createGetFilesBetweenTwoCommits() {
        return new GetFilesBetweenTwoCommits();
    }

    /**
     * Create an instance of {@link GetFilesBetweenTwoCommitsResponse }
     * 
     */
    public GetFilesBetweenTwoCommitsResponse createGetFilesBetweenTwoCommitsResponse() {
        return new GetFilesBetweenTwoCommitsResponse();
    }

    /**
     * Create an instance of {@link GetAllRevisionsResponse }
     * 
     */
    public GetAllRevisionsResponse createGetAllRevisionsResponse() {
        return new GetAllRevisionsResponse();
    }

    /**
     * Create an instance of {@link GetFilesOfCommitResponse }
     * 
     */
    public GetFilesOfCommitResponse createGetFilesOfCommitResponse() {
        return new GetFilesOfCommitResponse();
    }

    /**
     * Create an instance of {@link GetLatestRevision }
     * 
     */
    public GetLatestRevision createGetLatestRevision() {
        return new GetLatestRevision();
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
     * Create an instance of {@link GetFilesOfCommit }
     * 
     */
    public GetFilesOfCommit createGetFilesOfCommit() {
        return new GetFilesOfCommit();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesBetweenTwoCommitsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesBetweenTwoCommitsResponse")
    public JAXBElement<GetFilesBetweenTwoCommitsResponse> createGetFilesBetweenTwoCommitsResponse(GetFilesBetweenTwoCommitsResponse value) {
        return new JAXBElement<GetFilesBetweenTwoCommitsResponse>(_GetFilesBetweenTwoCommitsResponse_QNAME, GetFilesBetweenTwoCommitsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesOfCommit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesOfCommit")
    public JAXBElement<GetFilesOfCommit> createGetFilesOfCommit(GetFilesOfCommit value) {
        return new JAXBElement<GetFilesOfCommit>(_GetFilesOfCommit_QNAME, GetFilesOfCommit.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "echoResponse")
    public JAXBElement<EchoResponse> createEchoResponse(EchoResponse value) {
        return new JAXBElement<EchoResponse>(_EchoResponse_QNAME, EchoResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesOfCommitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.gitproxy.ubicropper.ubitech.eu/", name = "getFilesOfCommitResponse")
    public JAXBElement<GetFilesOfCommitResponse> createGetFilesOfCommitResponse(GetFilesOfCommitResponse value) {
        return new JAXBElement<GetFilesOfCommitResponse>(_GetFilesOfCommitResponse_QNAME, GetFilesOfCommitResponse.class, null, value);
    }

}
