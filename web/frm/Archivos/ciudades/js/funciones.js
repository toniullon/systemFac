/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarCiudad() {
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
            abrir();
            $("#id_ciudad").focus();
            $("#id_ciudad").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_ciudad").focus();
        }
    });
}

function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            if (json.nuevo === "true") {
                $("#botonNuevo").prop('disabled', true);
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#BotonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_ciudad", "#botonAgregar", true);
            } else {
                $("#botonNuevo").prop('disabled', false);
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#BotonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_ciudad", "#botonModificar", true);
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

function buscarNombreCiudad() {
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
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
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

function modificarCiudad() {
    var datosFormulario = $("#formPrograma").serialize();

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
            abrirm();
            $("#id_ciudad").focus();
            $("#id_ciudad").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarCiudad() {
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
            abrire();
            $("#Id_ciudad").focus();
            $("#id_ciudad").select();
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

function buscarCiudades() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {
                agregarCiudad();

            } else {
                //      alert("Nombre de ciudad repetido");
                abrirRe();
                $("#nombre_ciudad").val("");
                $("#nombre_ciudad").focus();

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

function buscarCiudadesM() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {
                modificarCiudad();

            } else {
                abrirRe();
                // alert("Nombre de ciudad Existente");
                //$("#nombre_ciudad").val("");
                $("#nombre_ciudad").focus();

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
    if ($("#nombre_ciudad").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Ciudad no puede estar vacio.");
        $("#nombre_ciudad").focus();
    }
    return valor;
}

function limpiarFormulario() {

    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");

}
function abrir() {
    document.getElementById("vent").style.display = "block";
    $("#botonNuevo").prop('disabled', true);
    $("#botonAgregar").prop('disabled', true);
    $("#botonModificar").prop('disabled', true);
    $("#BotonEliminar").prop('disabled', true);
    $("#botonSalir").prop('disabled', true);
}

function cerrar() {
    document.getElementById("vent").style.display = "none";
    $("#id_ciudad").focus();
    $("#id_ciudad").select();
    $("#botonSalir").prop('disabled', false);
}

function abrirm() {
    document.getElementById("ventM").style.display = "block";
    $("#botonNuevo").prop('disabled', true);
    $("#botonAgregar").prop('disabled', true);
    $("#botonModificar").prop('disabled', true);
    $("#BotonEliminar").prop('disabled', true);
    $("#botonSalir").prop('disabled', true);
}

function cerrarm() {
    document.getElementById("ventM").style.display = "none";
    $("#id_ciudad").focus();
    $("#id_ciudad").select();
}

function abrire() {
    document.getElementById("ventE").style.display = "block";
    $("#botonNuevo").prop('disabled', true);
    $("#botonAgregar").prop('disabled', true);
    $("#botonModificar").prop('disabled', true);
    $("#BotonEliminar").prop('disabled', true);
    $("#botonSalir").prop('disabled', true);
}

function cerrare() {
    document.getElementById("ventE").style.display = "none";
    $("#id_ciudad").focus();
    $("#id_ciudad").select();
    $("#botonSalir").prop('disabled', false);
}
function abrirRe() {
    document.getElementById("alerta").style.display = "block";
    $("#botonNuevo").prop('disabled', true);
    $("#botonAgregar").prop('disabled', true);
    $("#botonModificar").prop('disabled', true);
    $("#BotonEliminar").prop('disabled', true);
    $("#botonSalir").prop('disabled', true);
}
function cerrarRe() {
    document.getElementById("alerta").style.display = "none";
    $("#id_ciudad").focus();
    $("#id_ciudad").select();
    $("#botonSalir").prop('disabled', false);
}