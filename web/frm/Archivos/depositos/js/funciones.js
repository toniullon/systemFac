/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarDeposito() {
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
            $("#id_deposito").focus();
            $("#id_deposito").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_deposito").focus();
        }
    });
}

function buscarIdDeposito() {
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
            $("#id_deposito").val(json.id_deposito);
            $("#nombre_deposito").val(json.nombre_deposito);
            if (json.nuevo === "true") {
                $("#botonNuevo").prop('disabled', true);
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#BotonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_deposito", "#botonAgregar", true);
            } else {
                $("#botonNuevo").prop('disabled', false);
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#BotonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_deposito", "#botonModificar", true);
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

function buscarNombreDeposito() {
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
                $("#id_deposito").val(id);
                $("#nombre_deposito").focus();
                buscarIdDeposito();
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

function modificarDeposito() {
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
            $("#id_deposito").focus();
            $("#id_deposito").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarDeposito() {
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
            $("#Id_deposito").focus();
            $("#id_deposito").select();
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

function buscarDepositoV() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarDeposito.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },

        success: function (json) {

            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {
                agregarDeposito();

            } else {
                abrirRe();
                $("#nombre_deposito").val("");
                $("#nombre_deposito").focus();

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
function buscarDepositoM() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarDeposito.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },

        success: function (json) {

            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {
                modificarDeposito();

            } else {
                abrirRe();
                //  $("#nombre_deposito").val("");
                $("#nombre_deposito").focus();

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
    if ($("#nombre_deposito").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Deposito no puede estar vacio.");
        $("#nombre_deposito").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_deposito").val("0");
    $("#nombre_deposito").val("");
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
