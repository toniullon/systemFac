function buscarIdPedidoCliente() {
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_pedidocliente").val(json.id_pedidocliente);
            $("#fecha_pedidov").val(json.fecha_pedidov);
            $("#estado_pedidov").val(json.estado_pedidov);
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
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombrePedidoCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
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
                $("#id_pedidocliente").val(id);
                $("#estado_pedidov").focus();
                buscarIdPedidoCliente();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
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
    valor1 = $("#precioventa_producto").val();
    valor2 = $("#cantidad_detallepedidoc").val();
    /*valor3 = $("#porcentaje_iva").val();
     valor4 = $("#preciototal_venta").val();*/
    subtotal = valor1 * valor2;
    //totaliva = valor3 * valor4;
    //preciototal = subtotal + totaliva;
    //$("#preciototal_venta").val(preciototal);
    $("#preciototal_venta").val(subtotal);
}


function agregarPedidoCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //   limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_pedidocliente").val(json.id_pedidocliente);
            $("#id_pedidocliente").focus;
            $("#id_pedidocliente").select();
            buscarIdPedidoCliente();
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
function buscarPedidoDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            if (json.nuevo === "true") {
                AgregarPedidoDetalle();
            } else {
                alert("El producto ya existe");
                //    $("#nombre_ciudad").val("");
                //    $("#nombre_ciudad").focus();

            }
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
function modificarAjuste() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_pedidocliente").focus;
            $("#id_pedidocliente").select();
            buscarIdAjuste();
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
function eliminarPedido() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_pedidocliente").focus;
            $("#id_pedidocliente").select();
            location.reload();
            //   buscarIdPedidoCliente();
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


function validarFormularioPedidoCliente() {
    var valor = true;
    var canti = $("#cantidad_detallepedidoc").val();
    var produc = $("#id_producto").val();
    var motivo = $("#motivo_pedidocliente").val();
    if (motivo === "") {
        valor = false;
        $("#motivo_pedidocliente").val("");
        $("#motivo_pedidocliente").focus();
        $("#mensajes").html("Motivo No puede estar Vacio.");
    } else {
        if (produc === "0") {
            valor = false;
            $("#id_producto").val("");
            $("#id_producto").focus();
            $("#mensajes").html("Producto no puede estar vacio.");
        }
        if (canti === "0") {
            valor = false;
            $("#cantidad_detallepedidoc").val("");
            $("#cantidad_detallepedidoc").focus();
            $("#mensajes").html("Cantidad no puede estar vacia.");
        }
    }


    return valor;
}
function validarFormularioper() {
    var valor = true;

    if ($("#cantidad_detallepedidoc").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cantidad Vacia.");
        $("#cantidad_detallepedidoc").focus();
    }

    if ($("#cantidad_detallepedidoc").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Cantidad Vacia.");
        $("#cantidad_detallepedidoc").focus();
    }
    if ($("#codigo_producto").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Seleccione un Producto.");
        $("#codigo_producto").focus();
    }

    return valor;
}
function limpiarFormularioLinea() {
//alert("D");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#precioventa_producto").val("");
    $("#codigo_producto").val("");
    $("#codigo_producto").focus();
    $("#cantidad_detallepedidoc").val("");
    $("#preciototal_venta").val("0");
}
function limpiarFormulario() {
    $("#id_pedidocliente").val("0");
    // $("#id_usuario").val("");
    //  $("#nombre_usuario").val("");
    $("#fecha_pedidocliente").val("");
    $("#motivo_pedidocliente").val("");
}
function agregarLineas() {
//alert("hhh");
    $("#botonImprimir").prop('disabled', false);
    $("#codigo_producto").select();
    $("#codigo_producto").focus();
    $("#botonCambiaLinea").hide();
    $("#botonAgregaLinea").show();
    $("#id_detallepedidoc").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#precioventa_producto").val("0");
    $("#preciototal_venta").val("0");
    $("#cantidad_detallepedidoc").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleajuste", "#botonAgregarLinea", true);
}
function RestarLinea() {

    $("#id_pedidocliente_detalle").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#precioventa_producto").val("0");
    $("#cantidad_detallepedidoc").val("0");
    $("#preciototal_venta").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleajuste", "#botonAgregarLinea", false);
}



function editarLinea(id) {
    $("#id_detallepedidoc").val(id);
    $("#botonCambiaLinea").show();
    $("#botonAgregaLinea").hide();
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#cantidad_detallepedidoc").focus();
    $("#cantidad_detallepedidoc").select();
    $("#codigo_producto").prop('disabled', true);
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPedidoDetalle();
    //siguienteCampo("#cantidad_pedidocliente", "#botonModificarLinea", true);
}
// ajustesproductos
function buscarIdPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //  alert(datosFormulario);
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
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#precioventa_producto").val(json.precioventa_producto);
            $("#codigo_producto").val(json.codigo_producto);
            $("#cantidad_detallepedidoc").val(json.cantidad_detallepedidoc);
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
function SumarAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste = $("#id_pedidocliente").val();
    datosFormulario += "&id_pedidocliente=" + id_ajuste;
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/SumarAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAjuste();
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
function AgregarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedido = $("#id_pedidocliente").val();
    datosFormulario += "&id_pedidocliente=" + id_pedido;
    //  alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/AgregarDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.cantidad_stock !== -1) {
                if (json.mensaje === "Datos agregados.") {
                    $("#panelLinea").fadeOut("slow");
                    $("#panelPrograma").fadeIn("slow");
                    $("#codigo_producto").val("");
                    buscarIdPedidoCliente();
                } else {
                    alert("el producto ya existe en el pedido");
                    $("#id_producto").val("0");
                    //    $("#id_producto").focus();
                    //    $("#id_producto").select();
                    $("#nombre_producto").val("");
                    $("#precioventa_producto").val("");
                    $("#cantidad_detallepedidoc").val("");
                    $("#preciototal_venta").val("0");
                    $("#mensajes").html(json.mensaje);
                    $("#codigo_producto").val("");
                }
            } else {
                abrirRe();
                $("#mensajes").html(json.mensaje);
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
function modificarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedidocliente = $("#id_pedidocliente").val();
    datosFormulario += "&id_pedidocliente=" + id_pedidocliente;
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
            if (json.cantidad_stock !== -1) {
                if (json.mensaje === "Datos agregados.") {
                    alert("el producto ya existe en el pedido");
                    $("#id_producto").val("0");
                    $("#codigo_producto").select();
                    $("#codigo_producto").focus();
                    $("#nombre_producto").val("");
                    $("#precioventa_producto").val("");
                    $("#cantidad_detallepedidoc").val("");
                    $("#preciototal_venta").val("0");
                    $("#mensajes").html(json.mensaje);
                } else {
                    $("#codigo_producto").prop('disabled', false);
                    $("#panelLinea").fadeOut("slow");
                    $("#panelPrograma").fadeIn("slow");
                    $("#codigo_producto").prop('disabled', false);
                    limpiarFormularioLinea();
                    buscarIdPedidoCliente();
                }
            } else {
                abrirRe();
                $("#mensajes").html(json.mensaje);
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
function modificarAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste = $("#id_ajuste").val();
    datosFormulario += "&id_ajuste=" + id_ajuste;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAjuste();
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

function P(id) {

    $("#id_detallepedidoc").val(id);
    eliminarPedidoDetalle();
}
function eliminarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedidocliente = $("#id_pedidocliente").val();
    datosFormulario += "&id_pedidocliente=" + id_pedidocliente;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedidoCliente();
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
//// productos
function buscarCodigoProducto() {
    var datosFormulario = $("#formLinea").serialize();
    //alert("buscarCodigoProducto");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCodigoProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#codigo_producto").val(json.codigo_producto);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            //$("#precioventa").val(json.precioventa);
            $("#precioventa_producto").val(json.precioventa_producto);
            // alert(json.codigo_producto);
            $("#cantidad_detallepedidoc").focus();
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

function buscarIdProducto() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProducto.jsp',
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
            $("#precioventa_producto").val(json.precioventa_producto);
            // alert(json.codigo_producto);
            $("#cantidad_detallepedidoc").focus();
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

function buscarNombreProducto() {
    var datosFormulario = $("#formBuscar").serialize();
    // alert("buscarNombreProducto");  
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProducto.jsp',
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
                $("#id_producto").val(id);
                $("#nombre_producto").focus();
                cerrarbusqueda();
                buscarIdProducto();
                //$("#buscar").fadeOut("slow");
                // $("#panelLinea").fadeIn("slow");
                //  $("#parafondo").fadeOut("slow");

            });
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

function buscarNombreProductoC() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProductoC.jsp',
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
                $("#id_producto").val(id);
                $("#bnombre_producto").focus();
                buscarIdProducto();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
                $("#parafondo").fadeOut("slow");
            });
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

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";
    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }
}
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}
/*function validarFormulario() {
 var valor = true;
 if ($("#nombre_medida").val().length < 3) {
 valor = false;
 $("#mensajes").html("Nombre medidas no puede estar vacio.");
 $("#nombre_medida").focus();
 }
 return valor;
 }*/
function abrirRe() {
    document.getElementById("alerta").style.display = "block";
    $("#botonAgregaLinea").prop('disabled', true);
    $("#botonBuscarIdProducto").prop('disabled', true);
    $("#botonSalirLinea").prop('disabled', true);
}

function abrirNumero() {
    document.getElementById("NumeroPedido").style.display = "block";
    $("#botonAgregar").prop('disabled', true);
    $("#botonImprimir").prop('disabled', true);
    $("#botonEliminar").prop('disabled', true);
    $("#botonSalir").prop('disabled', true);
    setTimeout('cerrarN()', 800);
    /*$("#mas").prop('disabled', true);
     $("#botonBuscarIdPedidoCliente").prop('disabled', true);
     $("#menos").prop('disabled', true);
     $("#lapiz").prop('disabled', true);
     /* $("#botonAgregaLinea").prop('disabled', true);
     $("#botonBuscarIdProducto").prop('disabled', true);
     $("#botonSalirLinea").prop('disabled', true);*/
}

function cerrarN() {
// document.getElementById("NumeroPedido").style.display = "none";
    location.reload();
}

function cerrarRe() {
    document.getElementById("alerta").style.display = "none";
    $("#cantidad_detallepedidoc").val("");
    $("#cantidad_detallepedidoc").focus();
    $("#cantidad_detallepedidoc").select();
    $("#botonBuscarIdProducto").prop('disabled', false);
    $("#botonAgregaLinea").prop('disabled', false);
    $("#botonSalirLinea").prop('disabled', false);
}

function fecha() {
    var hoy = new Date().toJSON().slice(0, 10);
    console.log(hoy);
    $("#fecha_pedidov").val(hoy);
}

function cerrarbusqueda() {
    $("#panelLinea").fadeIn("slow");
    $("#parafondo").fadeOut("slow");
    $("#buscar").animate({opacity: "10", left: "+=50", width: "10", position: "absolute"})
    //.animate({opacity: "0.4", top: "+=160", height: "20", width: "80", fontSize: '0.5em'})
    //.animate({opacity: "1", left: "0", width: "260"}, "slow")
    //.animate({top: "0", width: "500", fontSize: '1.2em'}, "fast")
    // .slideUp()
    //.slideDown(1800)
    return false;
}