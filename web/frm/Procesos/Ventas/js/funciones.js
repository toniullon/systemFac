function buscarIdPedidoCliente() {
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
            $("#id_pedidocliente").val(json.id_pedidocliente);
            $("#fecha_pedidov").val(json.fecha_pedidov);
            $("#estado_pedidov").val(json.estado_pedidov);
            $("#contenidoDetalles").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonImprimir").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);

                //siguienteCampo("#id_tipoajuste", "#botonAgregar", true);
                $("#detalles").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonImprimir").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //$("#").prop('disabled', false);
                //siguienteCampo("#id_tipoajuste", "#botonModificar", true);
                $("#detalles").prop('hidden', false);
                $("#botonFacturar").prop('disabled', false);
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

function buscarIdPedidoFactura() {
    // alert("gggggg");
    var datosFormulario = $("#formProgramas").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    //  alert(datosFormulario);
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
            $("#numero_venta").val(json.id_pedidocliente);
            $("#fecha_venta").val(json.fecha_pedidov);
            //$("#fecha_venta").val(json.fecha_pedidov);
            $("#estado_venta").val(json.estado_pedidov);
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

function AgregarDetalleFactura() {
    var datosFormulario = $("#formPrograma").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/AgregarDetalleFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#numero_venta").val(json.id_pedidocliente);
            $("#fecha_venta").val(json.fecha_pedidov);
            //    $("#fecha_venta").val(json.fecha_pedidov);
            $("#estado_venta").val(json.estado_pedidov);
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_pedidocliente").val(json.id_pedidocliente);
            buscarIdPedidoCliente();
            $("#id_pedidocliente").focus;
            $("#id_pedidocliente").select();

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
            $("#mensajes").html("Cantidad ajuste no puede estar vacia.");
        }
    }


    return valor;

}
function limpiarFormulario() {
    $("#id_pedidocliente").val("0");
    // $("#id_usuario").val("");
    //  $("#nombre_usuario").val("");
    $("#fecha_pedidocliente").val("");
    $("#motivo_pedidocliente").val("");
}
function agregarLineas() {
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
    $("#id_producto").focus();
    $("#id_producto").select();
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
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#cantidad_detallepedidoc").focus();
    $("#cantidad_detallepedidoc").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPedidoDetalle();
    //siguienteCampo("#cantidad_pedidocliente", "#botonModificarLinea", true);
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

function cerrarRe() {
    document.getElementById("alerta").style.display = "none";
    $("#cantidad_detallepedidoc").val("");
    $("#cantidad_detallepedidoc").focus();
    $("#cantidad_detallepedidoc").select();
    $("#botonBuscarIdProducto").prop('disabled', false);
    $("#botonAgregaLinea").prop('disabled', false);
    $("#botonSalirLinea").prop('disabled', false);
}

function confirfacturar() {
    var datosFormulario = $("#formProgramas").serialize();
    var id_pedidocliente = $("#id_pedidocliente").val();
    datosFormulario += "&id_pedidocliente=" + id_pedidocliente;
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
            $("#FormPrograma").fadeIn("slow");
            $("#panelPrograma").fadeOut("slow");
            $("#id_persona").focus();
            $("#id_venta").val(json.id_venta);
            $("#condicion_venta").val(json.condicion_venta);
            buscarIdPedidoFactura();
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

function modificartota() {
    var datosFormulario = $("#formProgramaLinea").serialize();
    var total = $("#total").val();
    datosFormulario += "&total=" + total;
 //   alert(datosFormulario);
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
            // $("#panelLinea").fadeOut("slow");
         
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
function ModificarCobrados() {
    //tiene que limpiar y luego regresar al ingreso del pedido y si es posible una ventana de confirmacion
    var datosFormulario = $("#formProgramaLinea").serialize();
    var total = $("#total").val();
    datosFormulario += "&total=" + total;
   // alert(datosFormulario);
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
           setTimeout('location.reload()', 900);

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
function ModificarAnular() {
    //tiene que limpiar y luego regresar al ingreso del pedido y si es posible una ventana de confirmacion
    var datosFormulario = $("#formPrograma").serialize();
    var id_venta = $("#id_venta").val();
    var id_persona = $("#id_persona").val();
    datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;
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

function AgregarC() {

    var datosFormulario = $("#formPrograma").serialize();
    var id_venta = $("#id_venta").val();
    var id_persona = $("#id_persona").val();
    datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;


    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarClienteVenta.jsp',
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

            $("#id_venta").val(json.id_venta);
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

function AgregarSin() {

    var datosFormulario = $("#formPrograma").serialize();
    var id_venta = $("#id_venta").val();
    var id_persona = $("#id_persona").val();
    datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;


    //   alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarSinNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            // $("#id_persona").focus();
            buscarIdPersona();
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

function AgregarSin2() {

    var datosFormulario = $("#formPrograma").serialize();
    var id_venta = $("#id_venta").val();
    var id_persona = $("#id_persona").val();
    datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;


    //   alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarSinNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            // $("#id_persona").focus();
            $("#facturarsin").hide();
            $("#botonSino").hide();
            $("#botonCobrarmoda").show();
            $("#co").show();
            buscarIdPersona2();
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

function buscarIdPersona() {
    var datosFormulario = $("#formProgramaLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ruc_persona").val(json.ruc_persona);
            AgregarC();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo encontrar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdPersona2() {
    var datosFormulario = $("#formProgramaLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ruc_persona").val(json.ruc_persona);


        },
        error: function (e) {
            $("#mensajes").html("No se pudo encontrar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombrePersona() {
    var datosFormulario = $("#formBuscar").serialize();
    //   alert("datosFormulario");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePersona.jsp',
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
                $("#id_persona").val(id);
                $("#nombre_persona").focus();
                buscarIdPersona();
                $("#parafondo").fadeOut("slow");
                $("#buscar").fadeOut("slow");

                $("#FormPrograma").fadeIn("slow");
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

function cerrarCaja() {
    var datosFormulario = $("#formPrograma").serialize();
    //t(datosFormulario);

    console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/cerrarCaja.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_caja").val(json.id_caja);
            location.reload();
            // buscarIdCaja();
            $("#id_caja").focus;
            $("#id_caja").select();

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

function abrirCaja() {
    var datosFormulario = $("#formPrograma").serialize();
   // alert(datosFormulario);

    console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCaja.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            // limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            // $("#detalle").prop('hidden', false);
            $("#id_caja").val(json.id_caja);
            location.reload();
            // buscarIdCaja();
            $("#id_caja").focus;
            $("#id_caja").select();

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
function buscarIdCajas() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCaja.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            var monto = dar_formato_numero(json.monto_inicial, ",", ".");
            $("#mensajes").html(json.mensaje);
            $("#id_caja").val(json.id_caja);
            $("#fecha_apertura").val(json.fecha_apertura);
            $("#monto_inicial").val(monto);
            $("#estado_caja").val(json.estado_caja);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#monto_inicial").focus();
                $("#monto_inicial").select();
                $("#detalle").prop('hidden', true);//ocultar si no encuentra
                //siguienteCampo("#nombre_caja", "#botonAgregar", true);
            } else {

                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', true); //para ocultar si encuentra (true=para mostrar)
                $("#panelCaja").prop('hidden', true);
                $("#panelPrograma").css("display", "block");
                $("#id_pedidocliente").focus();
                $("#botonFacturar").prop('disabled', true);
                //siguienteCampo("#nombre_caja", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
            location.reload();
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdPagos() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_pago").val(json.id_pago);
            $("#forma_pago").val(json.forma_pago);

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
function buscarFormaPago() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarFormaPago.jsp',
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
                $("#id_pago").val(id);
                $("#forma_pago").focus();
                buscarIdPagos();
                $("#buscar").fadeOut("slow");
                $("#panelDetalleCaja").fadeIn("slow");
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
function cobro() {
    //tiene que limpiar y luego regresar al ingreso del pedido y si es posible una ventana de confirmacion
    var datosFormulario = $("#formPrograma").serialize();
    var id_venta = $("#id_venta").val();
    var id_persona = $("#id_persona").val();
    datosFormulario += "&id_venta=" + id_venta + "&id_persona=" + id_persona;
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/ModificarEstadoFacturaC.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //$("#FormPrograma").fadeIn("slow");
            // $("#panelPrograma").fadeOut("slow");
            // $("#panelDetalleCaja").css("display", "block");
            //  $("#panelPrograma").css("display", "none");
            $("#buscar").css("display", "none");
            $("#panelLinea").css("display", "none");
            //$("#FormPrograma").css("display", "none");
            $("#id_ventap").val(json.id_venta);
            $("#id_ventap").focus();
            buscarIdVentaC();
            //  $("#condicion_venta").val(json.condicion_venta);
            //   buscarIdPedidoFactura();
            // $("#panelLinea").fadeOut("slow");
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
function buscarIdVentaC() {
    var datosFormulario = $("#formLinea").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ventap").val(json.id_venta);
            $("#liqiva").val(json.liq10);
            $("#numero_ventap").val(json.numero_venta);
            $("#total").val(json.total);

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
function vuelto(e) {
    //document.getElementById(e.id).value;
    var valor1, valor2, vuelto;
    valor1 = $("#total").val();
    valor2 = $("#importe_caja").val();

    vuelto = (valor2 - valor1);
    if (vuelto > 0) {
        $("#vuelto").prop('style', 'color: green');
        $("#vuelto").val(vuelto);
        $("#botonPagar").prop('disabled', false);
    } else {
        if (vuelto < 0) {
            $("#vuelto").prop('style', 'color: red');
            $("#vuelto").val(vuelto);
            $("#botonPagar").prop('disabled', true);
        } else {
            $("#vuelto").prop('style', 'color: black');
            $("#vuelto").val(vuelto);
            $("#botonPagar").prop('disabled', false);

        }
    }


}


function agregarCajaDetalles() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;
    //  alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCajaDetalles.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelDetalleCaja").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            setTimeout('ModificarCobrados()', 900);
            // buscarIdCaja();

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
    if ($("#monto_inicial").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Ingrese el monto de la Caja.");
        $("#monto_inicial").focus();
    }
    return valor;
}

function validarFormulariope() {

    var valor = true;
    if ($("#nombre_persona").val().trim() === "") {
        valor = false;
        $("#facturarsin").show();
        $("#botonSino").show();
        $("#botonCobrarmoda").hide();
        $("#co").hide();
    }
    if ($("#ruc_persona").val().trim() === "") {
        valor = false;
        $("#facturarsin").show();
        $("#botonSino").show();
        $("#botonCobrarmoda").hide();
        $("#co").hide();
    }

    return valor;
}