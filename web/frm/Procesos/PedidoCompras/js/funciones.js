function buscarIdPedidoProveedor() {
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
            $("#id_pedidoproveedor").val(json.id_pedidoproveedor);
            $("#fecha_pedidop").val(json.fecha_pedidop);
            $("#correo_persona").val(json.correo_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ruc_persona").val(json.ruc_persona);
            $("#id_persona").val(json.id_persona);
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
            $("#mensajes").html("No se pudo recuperar los datos.");


        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombrePedidoProveedor() {
    var datosFormulario = $("#formBuscar").serialize();
    //  alert(datosFormulario);
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
                $("#id_pedidoproveedor").val(id);
                $("#estado_pedidop").focus();
                buscarIdPedidoProveedor();
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
    valor1 = $("#preciocompra_producto").val();
    valor2 = $("#cantidad_detallepedidop").val();
    /*valor3 = $("#porcentaje_iva").val();
     valor4 = $("#preciototal_venta").val();*/
    subtotal = valor1 * valor2;
    //totaliva = valor3 * valor4;
    //preciototal = subtotal + totaliva;
    //$("#preciototal_venta").val(preciototal);
    $("#preciototal_venta").val(subtotal);
}


function agregarPedidoProveedor() {
    var datosFormulario = $("#formPrograma").serialize();
    // alert(datosFormulario);
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
            $("#id_pedidoproveedor").val(json.id_pedidoproveedor);
            buscarIdPedidoProveedor();
            $("#id_pedidoproveedor").focus;
            $("#id_pedidoproveedor").select();

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
            $("#id_pedidoproveedor").focus;
            $("#id_pedidoproveedor").select();
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
            $("#id_pedidoproveedor").focus;
            $("#id_pedidoproveedor").select();
            location.reload();
            //   buscarIdPedidoProveedor();
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


function validarFormularioPedidoProveedor() {
    var valor = true;

    var canti = $("#cantidad_detallepedidop").val();
    var produc = $("#id_producto").val();

    if (produc === "0") {
        valor = false;
        $("#id_producto").val("");
        $("#id_producto").focus();
        $("#mensajes").html("Producto no puede estar vacio.");
    }
    if (canti === "0") {
        valor = false;
        $("#cantidad_detallepedidop").val("");
        $("#cantidad_detallepedidop").focus();
        $("#mensajes").html("Cantidad no puede estar vacia.");
    }
    if (canti === "") {
        valor = false;
        $("#cantidad_detallepedidop").val("");
        $("#cantidad_detallepedidop").focus();
        $("#mensajes").html("Cantidad no puede estar vacia.");
    }



    return valor;

}

function limpiarFormularioLinea() {
//alert("D");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#preciocompra_producto").val("");
    $("#codigo_producto").val("");
    $("#codigo_producto").focus();
    $("#cantidad_detallepedidop").val("");
    $("#preciototal_venta").val("0");
}
function limpiarFormulario() {
    location.reload();
}
function agregarLineas() {
//alert("hhh");
    $("#botonImprimir").prop('disabled', false);
    $("#botonCambiaLinea").hide();
    $("#botonAgregaLinea").show();
    $("#codigo_producto").val("0");
    $("#codigo_producto").focus();
    $("#codigo_producto").select();
    $("#id_detallepedidop").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#preciocompra_producto").val("0");
    $("#preciototal_venta").val("0");
    $("#cantidad_detallepedidop").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleajuste", "#botonAgregarLinea", true);
}
function RestarLinea() {

    $("#id_pedidoproveedor_detalle").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#preciocompra_producto").val("0");
    $("#cantidad_detallepedidop").val("0");
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
    $("#id_detallepedidop").val(id);
    $("#botonCambiaLinea").show();
    $("#botonAgregaLinea").hide();
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#cantidad_detallepedidop").focus();
    $("#cantidad_detallepedidop").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#codigo_producto").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPedidoDetalle();
    //siguienteCampo("#cantidad_pedidocliente", "#botonModificarLinea", true);
}
// ajustesproductos
function buscarIdPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();

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
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#preciocompra_producto").val(json.preciocompra_producto);
            $("#codigo_producto").val(json.codigo_producto);
            $("#cantidad_detallepedidop").val(json.cantidad_detallepedidop);

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
    var id_ajuste = $("#id_pedidoproveedor").val();
    datosFormulario += "&id_pedidoproveedor=" + id_ajuste;

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
    var id_pedido = $("#id_pedidoproveedor").val();
    datosFormulario += "&id_pedidoproveedor=" + id_pedido;
   //    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/AgregarDetalles.jsp',
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
                    buscarIdPedidoProveedor();
                } else {
                    alert("el producto ya existe en el pedido");
                    $("#id_producto").focus();
                    $("#id_producto").val("0");
                    $("#id_producto").focus();
                    $("#id_producto").select();
                    $("#nombre_producto").val("");
                    $("#preciocompra_producto").val("");
                    $("#cantidad_detallepedidop").val("");
                    $("#preciototal_venta").val("0");
                    $("#mensajes").html(json.mensaje);

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
    var id_pedidoproveedor = $("#id_pedidoproveedor").val();
    datosFormulario += "&id_pedidoproveedor=" + id_pedidoproveedor;
     // alert(datosFormulario);
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
                    $("#id_producto").focus();
                    $("#id_producto").select();
                    $("#nombre_producto").val("");
                    $("#preciocompra_producto").val("");
                    $("#cantidad_detallepedidop").val("");
                    $("#preciototal_venta").val("0");
                    $("#mensajes").html(json.mensaje);
                } else {
                    $("#panelLinea").fadeOut("slow");
                    $("#panelPrograma").fadeIn("slow");
                    $("#codigo_producto").prop('disabled', false);
                    limpiarFormularioLinea();
                    buscarIdPedidoProveedor();

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

    $("#id_detallepedidop").val(id);

    eliminarPedidoDetalle();

}
function eliminarPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_pedidoproveedor = $("#id_pedidoproveedor").val();
    datosFormulario += "&id_pedidoproveedor=" + id_pedidoproveedor;
    //   alert(datosFormulario);
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
            buscarIdPedidoProveedor();

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
            //$("#preciocompra").val(json.preciocompra);
            $("#preciocompra_producto").val(json.preciocompra_producto);
            // alert(json.codigo_producto);
            $("#cantidad_detallepedidop").focus();
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
            $("#preciocompra_producto").val(json.preciocompra_producto);
            // alert(json.codigo_producto);
            $("#cantidad_detallepedidop").focus();
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
                salirbuscar();
                $("#id_producto").val(id);
                $("#nombre_producto").focus();

                buscarIdProducto();

                //   $("#buscar").fadeOut("slow");
                //   $("#panelLinea").fadeIn("slow");
                //    $("#parafondo").fadeOut("slow");
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
                salirbuscar2();
                $("#bnombre_producto").focus();
                buscarIdProducto();

                $("#panelLinea").fadeIn("slow");

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
function numero(e) {
    key = e.keyCode || e.which;

    teclado = String.fromCharCode(key).toLowerCase();


    letras = " 1234567890+-";

    especiales = "8-13-37-38-46-164";

    teclado_especial = false;

    for (var i in especiales) {
        if (key == especiales[i]) {
            teclado_especial = true;
            break;
        }
    }
    if (letras.indexOf(teclado) == -1 && !teclado_especial) {
        //   $("#mensajes").html("solo numeros.");
        alert("solo numero");
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
     $("#botonBuscarIdPedidoProveedor").prop('disabled', true);
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
    $("#cantidad_detallepedidop").val("");
    $("#cantidad_detallepedidop").focus();
    $("#cantidad_detallepedidop").select();
    $("#botonBuscarIdProducto").prop('disabled', false);
    $("#botonAgregaLinea").prop('disabled', false);
    $("#botonSalirLinea").prop('disabled', false);
}

function fecha() {
    var hoy = new Date().toJSON().slice(0, 10);

    console.log(hoy);
    $("#fecha_pedidop").val(hoy);
}
function buscarIdPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    //  alert(datosFormulario);
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
            $("#ruc_persona").val(json.ruc_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#correo_persona").val(json.correo_persona);
            // AgregarP();
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
                salirbuscar();
                $("#id_persona").val(id);
                $("#nombre_persona").focus();
                buscarIdPersona();
                $("#parafondo").fadeOut("slow");
                //   $("#buscar").fadeOut("slow");

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
function salirbuscar() {
    $("#parafondo").fadeOut("slow");
    $("#buscar").animate({opacity: "10", left: "+=50", width: "10", position: "absolute"})
    $("#id_persona").focus();
    return false;
}
function salirbuscar2() {
    $("#parafondo").fadeOut("slow");
    $("#buscar").animate({opacity: "10", left: "+=50", width: "10", position: "absolute"})

    return false;
}
function AgregarP() {

    var datosFormulario = $("#formPrograma").serialize();
    var id_pedido = $("#id_pedido").val();
    var id_persona = $("#id_persona").val();
    datosFormulario += "&id_pedido=" + id_pedido + "&id_persona=" + id_persona;


    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/AgregarProveedor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_pedidoproveedor").val(json.id_pedidoproveedor);
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_persona").val().trim() === "") {
        valor = false;
        document.getElementById("validacion").innerHTML = "Seleccione Proveedor";
        $("#ruc_persona").focus();
    }
    if ($("#ruc_persona").val().trim() === "") {
        valor = false;
        document.getElementById("validacion").innerHTML = "Seleccione Proveedor";
        $("#ruc_persona").focus();
    }

    return valor;
}