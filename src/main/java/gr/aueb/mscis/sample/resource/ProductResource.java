package gr.aueb.mscis.sample.resource;

import static gr.aueb.mscis.sample.resource.PharmacyUri.PRODUCTS;

import java.net.URI;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.service.CatalogService;

@Path(PRODUCTS)
public class ProductResource extends AbstractResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<ProductInfo> listallProducts(){
        EntityManager em = getEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        List<ProductInfo> productInfo = ProductInfo.wrap(products);

        em.close();
        return productInfo;
    }

    @GET
    @Path("{productId:[xxx]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductInfo getProductDetails(@PathParam("productId") int productId) {

        EntityManager em = getEntityManager();

        CatalogService cs = new CatalogService(em);
        Product product = cs.findProductById(productId);

        ProductInfo productInfo = ProductInfo.wrap(product);
        em.close();

        return productInfo;

    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductInfo> searchProductByName(@QueryParam("name") String name) {

        EntityManager em = getEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByName(name);

        List<ProductInfo> productInfo = ProductInfo.wrap(products);

        em.close();
        return productInfo;

    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductInfo> searchProductByEofn(@QueryParam("eofn") String eofn) {

        EntityManager em = getEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByEOF(eofn);

        List<ProductInfo> productInfo = ProductInfo.wrap(products);

        em.close();
        return productInfo;
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductInfo> searchProductByCategory(@QueryParam("description") String description) {

        EntityManager em = getEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByCategory(description);

        List<ProductInfo> productInfo = ProductInfo.wrap(products);

        em.close();
        return productInfo;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductInfo productInfo) {

        EntityManager em = getEntityManager();

        Product product = productInfo.getProduct(em);

        CatalogService cs = new CatalogService(em);
        product = cs.save(product);

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI newProductUri = ub.path(Integer.toString(product.getId())).build();

        em.close();

        return Response.created(newProductUri).build();
    }

    /**
     * Update a specific product
     *
     * @param productInfo
     * A full representation of the product, including its id should be
     * submitted
     * @return
     */
    @PUT
    @Path("{productId:[0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(ProductInfo productInfo) {

        EntityManager em = getEntityManager();

        Product product = productInfo.getProduct(em);

        CatalogService cs = new CatalogService(em);
        product = cs.save(product);

        em.close();

        return Response.ok().build();
    }

    @DELETE
    @Path("{productId:[0-9]*}")
    public Response deleteProduct(@PathParam("productId") int productId) {

        EntityManager em = getEntityManager();

        CatalogService service = new CatalogService(em);
        boolean result = service.deleteProduct(productId);

        if (!result) {
            em.close();
            return Response.status(Status.NOT_FOUND).build();
        }

        em.close();
        return Response.ok().build();

    }

}
