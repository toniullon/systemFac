/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: "jsp/agregar.jsp",
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_producto").focus();
            $("#id_producto").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_producto").focus();
        }
    });
}

function buscarIdProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    //console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            var precioc = dar_formato_numero(json.preciocompra_producto, ",", ".");
            var preciov = dar_formato_numero(json.precioventa_producto, ",", ".");
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#codigo_producto").val(json.codigo_producto);
            $("#descripcion_producto").val(json.descripcion_producto);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            $("#id_medida").val(json.id_medida);
            $("#nombre_medida").val(json.nombre_medida);
            $("#id_rubro").val(json.id_rubro);
            $("#nombre_rubro").val(json.nombre_rubro);
            $("#stockmin_producto").val(json.stockmin_producto);
            $("#stockmax_producto").val(json.stockmax_producto);
            $("#preciocompra_producto").val(precioc);
            $("#precioventa_producto").val(preciov);
            $("#iva_producto").val(json.iva_producto);
            if (json.nuevo === "true") {
               // $("#botonNuevo").prop('disabled', true);
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#BotonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_estadoscivil", "#botonModificar", true);

            } else {
                $("#botonNuevo").prop('disabled', false);
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#BotonEliminar").prop('disabled', false);

            }
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

function buscarNombreProducto() {
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
                $("#id_producto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
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

function modificarProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: "jsp/modificar.jsp",
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_producto").focus();
            $("#id_producto").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: "jsp/eliminar.jsp",
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#Id_producto").focus();
            $("#id_producto").select();
        },
        error: function (json) {
            $("#mensaje").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdMarca() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);

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

function buscarMarca() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarMarca.jsp',
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
                $("#id_marca").val(id);
                $("#nombre_marca").focus();
                buscarIdMarca();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
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
//buscarmedida
function buscarIdMedida() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMedida.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_medida").val(json.id_medida);
            $("#nombre_medida").val(json.nombre_medida);

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

function buscarNombreMedida() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreMedida.jsp',
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
                $("#id_medida").val(id);
                $("#nombre_medida").focus();
                buscarIdMedida();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
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
//buscarRubro
function buscarIdRubro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdRubro.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_rubro").val(json.id_rubro);
            $("#nombre_rubro").val(json.nombre_rubro);

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

function buscarNombreRubro() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreRubro.jsp',
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
                $("#id_rubro").val(id);
                $("#nombre_rubro").focus();
                buscarIdRubro();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
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

//buscarProductos
function buscarProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Nombre del Producto Existe");
                $("#nombre_producto").val("");
                $("#nombre_producto").focus();
                //siguienteCampo("#descripcion_producto", "#botonModificar", true);
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

function buscarRucProducto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarRuc.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de RUC repetidos");
                $("#ruc_producto").val("");
                $("#ruc_producto").focus();
                //siguienteCampo("#direccion_producto", "#botonModificar", true);
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
//buscarcodigo
function buscarCodigoProducto() {
    var datosFormulario = $("#formPrograma").serialize();
  //  alert("G");
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCodigo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de codigos repetidos");
                $("#codigo_producto").val("");
                $("#codigo_producto").focus();
                //siguienteCampo("#descripcion_producto", "#botonModificar", true);
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_producto").val().trim() === "") {
        valor = false;
          document.getElementById("nomb").innerHTML = "campo vacio";
        $("#nombre_producto").focus();
    }
    if ($("#descripcion_producto").val().trim() === "") {
        valor = false;
       document.getElementById("descrip").innerHTML = "campo vacio";
        $("#descripcion_producto").focus();
    }
    if ($("#preciocompra_producto").val().trim() === "0") {
        valor = false;
      document.getElementById("compra").innerHTML = "tiene que ser mayor a 0";
        $("#preciocompra_producto").focus();
    }

    if ($("#codigo_producto").val().trim() === "") {
        valor = false;
       document.getElementById("codigo").innerHTML = "codigo invalido";
        $("#codigo_producto").focus();
    }

    if ($("#stockmin_producto").val().trim() === "0") {
        valor = false;
       document.getElementById("min").innerHTML = "tiene que ser mayor a 0";
        $("#stockmin_producto").focus();
    }

    if ($("#stockmax_producto").val().trim() === "0") {
        valor = false;
        document.getElementById("max").innerHTML = "tiene que ser mayor a 0";
        $("#stockmax_producto").focus();
    }
    
      if ($("#id_marca").val().trim() === "0") {
        valor = false;
       document.getElementById("marca").innerHTML = "seleccione una marca";
        $("#id_marca").focus();
    }
    
      if ($("#id_rubro").val().trim() === "0") {
        valor = false;
       document.getElementById("rubro").innerHTML = "seleccione un rubro";
        $("#id_rubro").focus();
    }
    
      if ($("#id_medida").val().trim() === "0") {
        valor = false;
       document.getElementById("medida").innerHTML = "seleccione una medida";
        $("#id_medida").focus();
    }

    if ($("#precioventa_producto").val().trim() === "0") {
        valor = false;
       document.getElementById("venta").innerHTML = "campo vacio";
        $("#precioventa_producto").focus();
    }

    return valor;
}

function limpiarFormulario() {
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#descripcion_producto").val("");
    $("#id_marca").val("0");
    $("#nombre_marca").val("");
    $("#id_medida").val("0");
    $("#nombre_medida").val("");
    $("#id_rubro").val("0");
    $("#nombre_rubro").val("");
    $("#stockmin_producto").val("0");
    $("#stockmax_producto").val("0");
    $("#codigo_producto").val("");
    $("#preciocompra_producto").val("");
    $("#precioventa_producto").val("");
    $("#iva_producto").val("");
}
