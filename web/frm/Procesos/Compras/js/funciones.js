function buscarIdPedidoProveedor() {
    var datosFormulario = $("#formProgramas").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_pedidoproveedor").val(json.id_pedidoproveedor);
            $("#fecha_pedidop").val(json.fecha_pedidop);
            $("#correo_personap").val(json.correo_persona);
            $("#nombre_personap").val(json.nombre_persona);
            $("#ruc_personap").val(json.ruc_persona);
            $("#id_personap").val(json.id_persona);
            $("#estado_pedidop").val(json.estado_pedidop);

            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);

                $("#botonEliminar").prop('disabled', true);

                //siguienteCampo("#id_tipoajuste", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                //      $("#botonImprimir").prop('disabled', false);
                $("#parafondo").fadeOut("slow");
                $("#botonImprimir").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //$("#").prop('disabled', false);
                //siguienteCampo("#id_tipoajuste", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
             document.getElementById("validacion").innerHTML = "Datos incorrecto (pedido no existe)";
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function confirfactura() {
    var datosFormulario = $("#formProgramas").serialize();
    var id_pedidoproveedor = $("#id_pedidoproveedor").val();
    datosFormulario += "&id_pedidoproveedor=" + id_pedidoproveedor;
    var fecha_pedidop = $("#fecha_pedidop").val();
    datosFormulario += "&fecha_pedidop=" + fecha_pedidop;
     alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarEstadoPedido.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // $("#panelLinea").fadeOut("slow");
            
            $("#id_persona").focus();
            $("#id_compra").val(json.id_compra);
          //  $("#condicion_compra").val(json.condicion_compra);
            ModificarFacturado();
            //AgregarDetalleFactura();
        },
        error: function (e) {

            $("#mensajes").html("No se pudo agregar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdPedidoFacturaC() {
    // alert("gggggg");
    var datosFormulario = $("#formProgramas").serialize();
    var id_compra = $("#id_compra").val();
    datosFormulario += "&id_compra=" + id_compra;
     alert("ESTO ES DE BUSCAR IDPEDIDOFACTURAC"+datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPedidoFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
         //   $("#numero_compra").val(json.id_pedidoproveedor);
            $("#fecha_compra").val(json.fecha_pedidop);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ruc_persona").val(json.ruc_persona);
            $("#id_persona").val(json.id_persona);
            $("#contenidoDetallep").html(json.contenido_detallep);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonImprimir").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);

                //siguienteCampo("#id_tipoajuste", "#botonAgregar", true);
                $("#detallep").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonImprimir").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //$("#").prop('disabled', false);
                //siguienteCampo("#id_tipoajuste", "#botonModificar", true);
                $("#detallep").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function editarLinea(id) {
    //  alert(id);
    $("#id_detallecompra").val(id);
    // $("#botonCambiaLinea").show();
    // $("#botonAgregaLinea").hide();
    $("#detalleFactura").fadeIn("slow");
    $("#FormPrograma").css("display", "none");
    $("#panelDiseño").fadeOut("slow");
    $("#cantidad_detallepedidop").focus();
    $("#cantidad_detallepedidop").select();
    // $("#botonAgregarLinea").prop('disabled', true);
    // $("#botonModificarLinea").prop('disabled', false);
    //  $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCompraDetalle();
    //siguienteCampo("#cantidad_pedidocliente", "#botonModificarLinea", true);
}
function buscarIdCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCompraDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#codigo_producto").val(json.codigo_producto);
            $("#preciocompra_producto").val(json.preciocompra_producto);
            $("#iva_producto").val(json.iva_producto);
            //$("#nombre_marca").val(json.nombre_marca);
            $("#cantidad_detallecompra").val(json.cantidad_detallecompra);
            //   $("#preciototal_compra").val(json.preciototal_compra);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_producto", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_producto", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function pretotal(e) {
    //Aqui crearemos una función para que de acuerdo a la cantidad de productos, se haga un sub total
    var valor1, valor2, /*valor3, valor4,*/ subtotal/*, totaliva, preciototal*/;
    valor1 = $("#preciocompra_producto").val();
    valor2 = $("#cantidad_entrante").val();
    /*valor3 = $("#porcentaje_iva").val();
     valor4 = $("#preciototal_venta").val();*/
    subtotal = valor1 * valor2;
    //totaliva = valor3 * valor4;
    //preciototal = subtotal + totaliva;
    //$("#preciototal_venta").val(preciototal);
    $("#preciototal_detallepedidop").val(subtotal);
}

function faltante(e) {
    //Aqui crearemos una función para que de acuerdo a la cantidad de productos, se haga un sub total
    var valor1, valor2, /*valor3, valor4,*/ subtotal/*, totaliva, preciototal*/;

    valor2 = $("#cantidad_entrantes").val();
    valor1 = $("#cantidad_entrante").val();
    /*valor3 = $("#porcentaje_iva").val();
     valor4 = $("#preciototal_venta").val();*/
    subtotal = valor2 - valor1;
    //totaliva = valor3 * valor4;
    //preciototal = subtotal + totaliva;
    //$("#preciototal_venta").val(preciototal);
    $("#cantidad_faltante").val(subtotal);
}

function modificarCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_compra = $("#id_compra").val();
    datosFormulario += "&id_compra=" + id_compra;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCompraDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            limpiarFormularioLinea();
            buscarIdPedidoFacturaC();
            $("#detalleFactura").fadeOut("slow");
            $("#FormPrograma").fadeIn("slow");
        },
        error: function (e) {
            alert("datos repetidos");
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function limpiarFormularioLinea() {
//alert("D");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#preciocompra_producto").val("");
    $("#cantidad_detallecompra").val("");
    $("#preciototal_compra").val("0");
}
function ModificarAnular() {
    //tiene que limpiar y luego regresar al ingreso del pedido y si es posible una ventana de confirmacion
    var datosFormulario = $("#formProgramaLinea").serialize();
    // var id_venta = $("#id_venta").val();
    //  var id_persona = $("#id_persona").val();
    //  datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;
    //   alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarAnulado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // $("#panelLinea").fadeOut("slow");
            $("#FormPrograma").fadeIn("slow");
            $("#panelPrograma").fadeOut("slow");
            $("#ok").css("display", "block");
            $("#ok1").css("display", "none");
            $("#ok2").css("display", "none");
            $("#id_venta").val(json.id_venta);
            setTimeout(' location.reload()', 1000);

            //  $("#condicion_venta").val(json.condicion_venta);
            //   buscarIdPedidoFactura();

        },
        error: function (e) {

            $("#mensajes").html("No se pudo agregar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function ModificarCobrado() {
    //tiene que limpiar y luego regresar al ingreso del pedido y si es posible una ventana de confirmacion
    var datosFormulario = $("#formProgramaLinea").serialize();
    // var id_venta = $("#id_venta").val();
    //  var id_persona = $("#id_persona").val();
    //  datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;
    //   alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarCobrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // $("#panelLinea").fadeOut("slow");

            $("#id_venta").val(json.id_venta);
            modificartota();

            //  $("#condicion_venta").val(json.condicion_venta);
            //   buscarIdPedidoFactura();

        },
        error: function (e) {

            $("#mensajes").html("No se pudo agregar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function P(id) {

    $("#id_detallecompra").val(id);
    eliminarCompraDetalle();

}
function eliminarCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_compra = $("#id_compra").val();
    datosFormulario += "&id_compra=" + id_compra;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCompraDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            //  $("#panelLinea").fadeOut("slow");
            //  $("#panelPrograma").fadeIn("slow");
            buscarIdPedidoFacturaC();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}

function CargarNumero() {
    var datosFormulario = $("#formProgramaLinea").serialize();
//alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/numerofactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
          

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos de venta.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdTotal() {
    var datosFormulario = $("#formProgramaLinea").serialize();
//alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCompra.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //        $("#id_ventap").val(json.id_venta);
            $("#liqiva").val(json.liq10);
            // $("#numero_ventap").val(json.numero_venta);
            $("#total").val(json.total);
            CargarNumero();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos de venta.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificartota() {
    var datosFormulario = $("#formProgramaLinea").serialize();
    var total = $("#total").val();
    datosFormulario += "&total=" + total;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarTotal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //  setTimeout(' location.reload()', 1000);
            // $("#panelLinea").fadeOut("slow");
            imp();
        },
        error: function (e) {

            $("#mensajes").html("No se pudo agregar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_personap").val().trim() === "") {
        valor = false;
        document.getElementById("validacion").innerHTML = "Ingrese N° de pedido";
        $("#id_pedidoproveedor").focus();
        $("#id_pedidoproveedor").select();
    }
    if ($("#ruc_personap").val().trim() === "") {
        valor = false;
        document.getElementById("validacion").innerHTML = "Ingrese N° de pedido";
        $("#id_pedidoproveedor").focus();
        $("#id_pedidoproveedor").select();
    }

    return valor;
}

function validarFormularioLinea() {
    var valor = true;
    if ($("#cantidad_detallecompra").val().trim() === "0") {
        valor = false;
        document.getElementById("validacion2").innerHTML = "Ingrese una cantidad mayor a cero";
        $("#cantidad_detallecompra").focus();

    }
    if ($("#cantidad_detallecompra").val().trim() === "") {
        valor = false;
        document.getElementById("validacion2").innerHTML = "Ingrese Cantidad";
        $("#cantidad_detallecompra").focus();

    }
    if ($("#preciocompra_producto").val().trim() === "0") {
        valor = false;
        document.getElementById("validacion3").innerHTML = "Precio no puede existir, vuelva a cargar";
        $("#preciocompra_producto").focus();

    }
    if ($("#preciocompra_producto").val().trim() === "") {
        valor = false;
        document.getElementById("validacion3").innerHTML = "Ingrese Precio";
        $("#preciocompra_producto").focus();

    }
    return valor;
}

function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}
function editarDepo(id) {
    // alert("F");
    $("#id_detallepedidop").val(id);
    $("#Deposito").show();
    $("#panelDiseño").fadeOut("hide");
    buscarIdPedidoDetalle();
}
function buscarIdPedidoDetalle() {
    var datosFormulario = $("#formDeposi").serialize();

    //     alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_productos").val(json.id_producto);
            $("#nombre_productos").val(json.nombre_producto);
            $("#preciocompra_producto").val(json.preciocompra_producto);
            $("#codigo_productos").val(json.codigo_producto);
            $("#cantidad_entrantes").val(json.cantidad_faltantes);
            $("#cantidad_detallepedidop").val(json.cantidad_detallepedidop);
            $("#descripcion_producto").val(json.descripcion_producto);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarFaltante() {
    var datosFormulario = $("#formDeposi").serialize();
    var id_pedidoproveedor = $("#id_pedidoproveedor").val();
    datosFormulario += "&id_pedidoproveedor=" + id_pedidoproveedor;
    //  alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.mensaje === "Datos agregados.") {
                /*  alert("el producto ya existe en el pedido");
                 $("#id_producto").val("0");
                 $("#id_producto").focus();
                 $("#id_producto").select();
                 $("#nombre_producto").val("");
                 $("#preciocompra_producto").val("");
                 $("#cantidad_detallepedidop").val("");
                 $("#preciototal_venta").val("0");
                 $("#mensajes").html(json.mensaje);*/
            } else {
                $("#Deposito").hide();
                $("#panelDiseño").show();
                /* $("#panelLinea").fadeOut("slow");
                 $("#panelPrograma").fadeIn("slow");
                 $("#codigo_producto").prop('disabled', false);*/
                limpiarFormularioDeposito();
                buscarIdPedidoProveedor();

            }


        },
        error: function (e) {
            alert("datos repetidos");
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function limpiarFormularioDeposito() {
//alert("D");
    $("#id_producto").val("");
    $("#nombre_productos").val("");
    $("#preciocompra_producto").val("");
    $("#codigo_productos").val("");
    $("#cantidad_detallepedidop").val("");
    $("#cantidad_entrante").val("");
    $("#cantidad_faltante").val("");
    $("#descripcion_producto").val("");
    $("#id_detallepedidop").val("")
}
function validarEstado() {

    if ($("#cantidad_faltante").val().trim() === "0") {
        //  alert("DFG");
        ModificarFacturado();
    } else {
        ModificarPendiente();
    }
}
function ModificarFacturado() {
    var datosFormulario = $("#formProgramas").serialize();
alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarFacturado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // $("#panelLinea").fadeOut("slow");
            $("#panelDiseño").fadeOut("slow");
            $("#FormPrograma").fadeIn("slow");
            buscarIdPedidoFacturaC();
            //  $("#condicion_venta").val(json.condicion_venta);
            //   buscarIdPedidoFactura();

        },
        error: function (e) {

            $("#mensajes").html("No se pudo agregar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function ModificarPendiente() {
    var datosFormulario = $("#formDeposi").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarPendiente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            // $("#panelLinea").fadeOut("slow");
            modificarFaltante();

            //  $("#condicion_venta").val(json.condicion_venta);
            //   buscarIdPedidoFactura();

        },
        error: function (e) {

            $("#mensajes").html("No se pudo agregar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreDeposito() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreDeposito.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");

                $("#id_deposito").val(id);
                $("#nombre_deposito").focus();
                salide();
                $("#Deposito").fadeIn("slow");
                buscarIdDeposito();
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function buscarIdDeposito() {
    var datosFormulario = $("#formDeposi").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDeposito.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_deposito").val(json.id_deposito);
            $("#nombre_deposito").val(json.nombre_deposito);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
 function salide() {
        $("#buscar2").fadeOut("slow");
        $("#buscar2").animate({opacity: "10", left: "0", width: "10", position: "absolute"})

        return false;
    }