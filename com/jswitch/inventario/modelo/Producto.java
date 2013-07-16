package com.jswitch.inventario.modelo;

import com.jswitch.auditoria.modelo.Auditable;
import com.jswitch.auditoria.modelo.AuditoriaBasica;
import com.jswitch.base.modelo.Dominios.ClaseProducto;
import com.jswitch.base.modelo.Dominios.TipoCodigoBarras;
import com.jswitch.base.modelo.Dominios.TipoDemanda;
import com.jswitch.base.modelo.Dominios.TipoImpuesto;
import com.jswitch.base.modelo.Dominios.TipoOperacionCalculoPrecio;
import com.jswitch.base.modelo.Dominios.TipoPrecioVenta;
import com.jswitch.base.modelo.Dominios.TipoProducto;
import com.jswitch.base.modelo.Dominios.UnidadTiempo;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.persona.modelo.dominio.TipoDireccion;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 *  Clase Asociativa la persona y el tipo de direccion
 *  @version 1.0 22/05/2009
 *  @since JDK 1.5
 *  @see Persona
 *  @see TipoDireccion
 * @author Nelson Moncada
 */
@Entity
@Table(name="INVENT_Producto")
public class Producto extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoProducto tipoProducto;

    @ManyToOne
    @BusinessKey
    private Persona fabricante;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private ClaseProducto claseProducto;

    /**
     * Direccion de la persona
     */
    @Column
    @Size(min = 2, max = 255)
    @BusinessKey
    private String nombre;
    /**
     * zip code
     */
    @Column
    @BusinessKey
    private String nombreCorto;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Integer numeroPiezaModelo;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float peso;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaPeso;

     /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float volumen;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaVolumen;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float anchura;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaAnchura;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float altura;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaAltura;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float largo;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaLargo;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float porcAumentoDescuentoPrecVentaDetail;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Integer precisionPrecVentaDetail;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float aumentoDescuentoPrecVentaDetail;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float precioVentaDetail;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoOperacionCalculoPrecio tipoOperacionCalculoPrecioVentaDetail;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoPrecioVenta tipoPrecioVentaDetail;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoDemanda tipoDemanda;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float porcAumentoDescuentoPrecVentaMayor;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Integer precisionPrecVentaMayor;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float aumentoDescuentoPrecVentaMayor;

    /**
     * ubicacion geografica
     */
    @Column
    @BusinessKey
    private Float precioVentaMayor;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoOperacionCalculoPrecio tipoOperacionCalculoPrecioVentaMayor;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoPrecioVenta tipoPrecioVentaMayor;

    /**
     * Tipo de cedula de persona
     */
    @Column
    private Boolean aplicaImpuesto;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoImpuesto tipoImpuesto;

    /**
     *
     */
    @Column
    private Float porcImpuesto;

    /**
     *
     */
    @Column
    private Float impuestoDemas;

    /**
     * Observacion de la direccion
     */
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @BusinessKey
    private List<Observacion> observaciones= new ArrayList<Observacion>(0);

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String descripcionEtiqueta;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String descripcion;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String descripcionCorta;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String instrucciones;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String detalles;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String codigoBarras;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String codigoBarrasViejo;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedida;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaPrecioventaDetail;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaPrecioventaMayor;

    /**
     *
     */
     @ManyToOne
     private UnidadMedida unidadMedidaVenta;



    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String RFID;

    /**
     * Observacion de la direccion
     */
    @Column
    @BusinessKey
    private String RFIDAnterior;

    /**
     *
     */
    @Column
    private Integer tiempoVida;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private UnidadTiempo unidadTiempoVida;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoCodigoBarras tipoCodigoBarras;

    /**
     * Tipo de cedula de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoCodigoBarras tipoCodigoBarrasViejo;
    
    /**
     * Tipo de direccion que posee esta direccion
     */
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private Almacen almacen;

    /**
     *
     */
    @Column
    private Integer nivelMinimoExistencia;

    /**
     * Coleccion de documentos anexos de la persona
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Documento> documentos = new ArrayList<Documento>(0);


    /**
     *
     */
    @Version
    @Column(name = "OPTLOCK")
    private Integer optLock;

    /**
     *
     */
    @OneToMany(fetch=FetchType.LAZY)
    private List<CategoriaProducto> categorias= new ArrayList<CategoriaProducto>(0);

    /**
     *
     */
    @OneToMany(fetch=FetchType.LAZY)
    private List<ProductoSustituto> productosSustitutos= new ArrayList<ProductoSustituto>(0);

    /**
     *
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public Producto() {
        this.claseProducto=ClaseProducto.A;
        this.peso=0.0f;
        this.volumen=0.0f;
        this.altura=0.0f;
        this.anchura=0.0f;
        this.largo=0.0f;
        this.tipoDemanda=TipoDemanda.REGULAR;
        this.precioVentaDetail=0.0f;
        this.precioVentaMayor=0.0f;
        this.impuestoDemas=0.0f;
        this.porcImpuesto=0.0f;
        this.porcAumentoDescuentoPrecVentaDetail=0.0f;
        this.porcAumentoDescuentoPrecVentaMayor=0.0f;
        this.nivelMinimoExistencia=0;
        this.aplicaImpuesto=true;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    @Override
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getRFIDAnterior() {
        return RFIDAnterior;
    }

    public void setRFIDAnterior(String RFIDAnterior) {
        this.RFIDAnterior = RFIDAnterior;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getAnchura() {
        return anchura;
    }

    public void setAnchura(Float anchura) {
        this.anchura = anchura;
    }

    public Boolean getAplicaImpuesto() {
        return aplicaImpuesto;
    }

    public void setAplicaImpuesto(Boolean aplicaImpuesto) {
        this.aplicaImpuesto = aplicaImpuesto;
    }

    public Float getAumentoDescuentoPrecVentaDetail() {
        return aumentoDescuentoPrecVentaDetail;
    }

    public void setAumentoDescuentoPrecVentaDetail(Float aumentoDescuentoPrecVentaDetail) {
        this.aumentoDescuentoPrecVentaDetail = aumentoDescuentoPrecVentaDetail;
    }

    public Float getAumentoDescuentoPrecVentaMayor() {
        return aumentoDescuentoPrecVentaMayor;
    }

    public void setAumentoDescuentoPrecVentaMayor(Float aumentoDescuentoPrecVentaMayor) {
        this.aumentoDescuentoPrecVentaMayor = aumentoDescuentoPrecVentaMayor;
    }

    public List<CategoriaProducto> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaProducto> categorias) {
        this.categorias = categorias;
    }

    public ClaseProducto getClaseProducto() {
        return claseProducto;
    }

    public void setClaseProducto(ClaseProducto claseProducto) {
        this.claseProducto = claseProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarrasViejo() {
        return codigoBarrasViejo;
    }

    public void setCodigoBarrasViejo(String codigoBarrasViejo) {
        this.codigoBarrasViejo = codigoBarrasViejo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionEtiqueta() {
        return descripcionEtiqueta;
    }

    public void setDescripcionEtiqueta(String descripcionEtiqueta) {
        this.descripcionEtiqueta = descripcionEtiqueta;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Persona getFabricante() {
        return fabricante;
    }

    public void setFabricante(Persona fabricante) {
        this.fabricante = fabricante;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Float getImpuestoDemas() {
        return impuestoDemas;
    }

    public void setImpuestoDemas(Float impuestoDemas) {
        this.impuestoDemas = impuestoDemas;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Integer getNivelMinimoExistencia() {
        return nivelMinimoExistencia;
    }

    public void setNivelMinimoExistencia(Integer nivelMinimoExistencia) {
        this.nivelMinimoExistencia = nivelMinimoExistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Integer getNumeroPiezaModelo() {
        return numeroPiezaModelo;
    }

    public void setNumeroPiezaModelo(Integer numeroPiezaModelo) {
        this.numeroPiezaModelo = numeroPiezaModelo;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getPorcAumentoDescuentoPrecVentaDetail() {
        return porcAumentoDescuentoPrecVentaDetail;
    }

    public void setPorcAumentoDescuentoPrecVentaDetail(Float porcAumentoDescuentoPrecVentaDetail) {
        this.porcAumentoDescuentoPrecVentaDetail = porcAumentoDescuentoPrecVentaDetail;
    }

    public Float getPorcAumentoDescuentoPrecVentaMayor() {
        return porcAumentoDescuentoPrecVentaMayor;
    }

    public void setPorcAumentoDescuentoPrecVentaMayor(Float porcAumentoDescuentoPrecVentaMayor) {
        this.porcAumentoDescuentoPrecVentaMayor = porcAumentoDescuentoPrecVentaMayor;
    }

    public Float getPorcImpuesto() {
        return porcImpuesto;
    }

    public void setPorcImpuesto(Float porcImpuesto) {
        this.porcImpuesto = porcImpuesto;
    }

    public Float getPrecioVentaDetail() {
        return precioVentaDetail;
    }

    public void setPrecioVentaDetail(Float precioVentaDetail) {
        this.precioVentaDetail = precioVentaDetail;
    }

    public Float getPrecioVentaMayor() {
        return precioVentaMayor;
    }

    public void setPrecioVentaMayor(Float precioVentaMayor) {
        this.precioVentaMayor = precioVentaMayor;
    }

    public Integer getPrecisionPrecVentaDetail() {
        return precisionPrecVentaDetail;
    }

    public void setPrecisionPrecVentaDetail(Integer precisionPrecVentaDetail) {
        this.precisionPrecVentaDetail = precisionPrecVentaDetail;
    }

    public Integer getPrecisionPrecVentaMayor() {
        return precisionPrecVentaMayor;
    }

    public void setPrecisionPrecVentaMayor(Integer precisionPrecVentaMayor) {
        this.precisionPrecVentaMayor = precisionPrecVentaMayor;
    }

    public List<ProductoSustituto> getProductosSustitutos() {
        return productosSustitutos;
    }

    public void setProductosSustitutos(List<ProductoSustituto> productosSustitutos) {
        this.productosSustitutos = productosSustitutos;
    }

    public TipoCodigoBarras getTipoCodigoBarras() {
        return tipoCodigoBarras;
    }

    public void setTipoCodigoBarras(TipoCodigoBarras tipoCodigoBarras) {
        this.tipoCodigoBarras = tipoCodigoBarras;
    }

    public TipoCodigoBarras getTipoCodigoBarrasViejo() {
        return tipoCodigoBarrasViejo;
    }

    public void setTipoCodigoBarrasViejo(TipoCodigoBarras tipoCodigoBarrasViejo) {
        this.tipoCodigoBarrasViejo = tipoCodigoBarrasViejo;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public TipoOperacionCalculoPrecio getTipoOperacionCalculoPrecioVentaDetail() {
        return tipoOperacionCalculoPrecioVentaDetail;
    }

    public void setTipoOperacionCalculoPrecioVentaDetail(TipoOperacionCalculoPrecio tipoOperacionCalculoPrecioVentaDetail) {
        this.tipoOperacionCalculoPrecioVentaDetail = tipoOperacionCalculoPrecioVentaDetail;
    }

    public TipoOperacionCalculoPrecio getTipoOperacionCalculoPrecioVentaMayor() {
        return tipoOperacionCalculoPrecioVentaMayor;
    }

    public void setTipoOperacionCalculoPrecioVentaMayor(TipoOperacionCalculoPrecio tipoOperacionCalculoPrecioVentaMayor) {
        this.tipoOperacionCalculoPrecioVentaMayor = tipoOperacionCalculoPrecioVentaMayor;
    }

    public TipoPrecioVenta getTipoPrecioVentaDetail() {
        return tipoPrecioVentaDetail;
    }

    public void setTipoPrecioVentaDetail(TipoPrecioVenta tipoPrecioVentaDetail) {
        this.tipoPrecioVentaDetail = tipoPrecioVentaDetail;
    }

    public TipoPrecioVenta getTipoPrecioVentaMayor() {
        return tipoPrecioVentaMayor;
    }

    public void setTipoPrecioVentaMayor(TipoPrecioVenta tipoPrecioVentaMayor) {
        this.tipoPrecioVentaMayor = tipoPrecioVentaMayor;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public UnidadMedida getUnidadMedidaAltura() {
        return unidadMedidaAltura;
    }

    public void setUnidadMedidaAltura(UnidadMedida unidadMedidaAltura) {
        this.unidadMedidaAltura = unidadMedidaAltura;
    }

    public UnidadMedida getUnidadMedidaAnchura() {
        return unidadMedidaAnchura;
    }

    public void setUnidadMedidaAnchura(UnidadMedida unidadMedidaAnchura) {
        this.unidadMedidaAnchura = unidadMedidaAnchura;
    }

    public UnidadMedida getUnidadMedidaLargo() {
        return unidadMedidaLargo;
    }

    public void setUnidadMedidaLargo(UnidadMedida unidadMedidaLargo) {
        this.unidadMedidaLargo = unidadMedidaLargo;
    }

    public UnidadMedida getUnidadMedidaPeso() {
        return unidadMedidaPeso;
    }

    public void setUnidadMedidaPeso(UnidadMedida unidadMedidaPeso) {
        this.unidadMedidaPeso = unidadMedidaPeso;
    }

    public UnidadMedida getUnidadMedidaVenta() {
        return unidadMedidaVenta;
    }

    public void setUnidadMedidaVenta(UnidadMedida unidadMedidaVenta) {
        this.unidadMedidaVenta = unidadMedidaVenta;
    }

    public UnidadMedida getUnidadMedidaVolumen() {
        return unidadMedidaVolumen;
    }

    public void setUnidadMedidaVolumen(UnidadMedida unidadMedidaVolumen) {
        this.unidadMedidaVolumen = unidadMedidaVolumen;
    }

    public Float getVolumen() {
        return volumen;
    }

    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    public Integer getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(Integer tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public UnidadTiempo getUnidadTiempoVida() {
        return unidadTiempoVida;
    }

    public void setUnidadTiempoVida(UnidadTiempo unidadTiempoVida) {
        this.unidadTiempoVida = unidadTiempoVida;
    }

    public TipoDemanda getTipoDemanda() {
        return tipoDemanda;
    }

    public void setTipoDemanda(TipoDemanda tipoDemanda) {
        this.tipoDemanda = tipoDemanda;
    }

    public UnidadMedida getUnidadMedidaPrecioventaDetail() {
        return unidadMedidaPrecioventaDetail;
    }

    public void setUnidadMedidaPrecioventaDetail(UnidadMedida unidadMedidaPrecioventaDetail) {
        this.unidadMedidaPrecioventaDetail = unidadMedidaPrecioventaDetail;
    }

    public UnidadMedida getUnidadMedidaPrecioventaMayor() {
        return unidadMedidaPrecioventaMayor;
    }

    public void setUnidadMedidaPrecioventaMayor(UnidadMedida unidadMedidaPrecioventaMayor) {
        this.unidadMedidaPrecioventaMayor = unidadMedidaPrecioventaMayor;
    }


}
