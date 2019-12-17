/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarPersona() {
    var datosFormulario = $("#formPrograma").serialize();
   // alert(datosFormulario);
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
            $("#uno").show();
            $("#dos").show();
            $("#tres").show();
            $("#correo_persona").show();
            $("#correo").show();
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_persona").focus();
        }
    });
}

function buscarIdPersona() {
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

            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#apellido_persona").val(json.apellido_persona);
            $("#direccion_persona").val(json.direccion_persona);
            $("#telefono_persona").val(json.telefono_persona);
            $("#correo_persona").val(json.correo_persona);
            $("#ruc_persona").val(json.ruc_persona);

            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#id_tipopersona").val(json.id_tipopersona);
            $("#nombre_tipopersona").val(json.nombre_tipopersona);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);



            if (json.nuevo === "true") {
                $("#botonNuevo").prop('disabled', true);
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#BotonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_estadoscivil", "#botonModificar", true);

            } else {
                $("#botonNuevo").prop('disabled', false);
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#BotonEliminar").prop('disabled', false);
                $("#uno").show();
                $("#dos").show();
                $("#tres").show();
                $("#correo_persona").show();
                $("#correo").show();
                siguienteCampo("#nombre_persona", "#botonModificar", true);
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

function buscarNombrePersona() {
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
                $("#id_persona").val(id);
                $("#nombre_persona").focus();
                buscarIdPersona();
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

function modificarPersona() {
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
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarPersona() {
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
            $("#Id_persona").focus();
            $("#id_persona").select();
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

function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);

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

function buscarCiudad() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCiudad.jsp',
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

function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
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
//buscartipopersona
function buscarIdTipopersona() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipopersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipopersona").val(json.id_tipopersona);
            $("#nombre_tipopersona").val(json.nombre_tipopersona);

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

function buscarNombreTipopersona() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTipopersona.jsp',
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
                $("#id_tipopersona").val(id);
                $("#nombre_tipopersona").focus();
                buscarIdTipopersona();
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
//buscarEstado_Civil
function buscarIdEstado_Civil() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstado_Civil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);

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
function buscarNombreEstado_Civil() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstado_Civil.jsp',
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
                $("#id_estadocivil").val(id);
                $("#nombre_estadocivil").focus();
                buscarIdEstado_Civil();
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

//buscarPersonas
function buscarPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarPersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de nombre repetidos");
                $("#nombre_persona").val("");
                $("#nombre_persona").focus();
                //siguienteCampo("#direccion_persona", "#botonModificar", true);
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

function buscarRucPersona() {
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
                $("#ruc_persona").val("");
                $("#ruc_persona").focus();
                //siguienteCampo("#direccion_persona", "#botonModificar", true);
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
//buscarapellido*/

function buscarTelefonoPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarTelefono.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de telefono repetidos");
                $("#telefono_persona").val("");
                $("#telefono_persona").focus();
                //siguienteCampo("#direccion_persona", "#botonModificar", true);
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
//comparar datos repetidos
function buscarCorreoPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCorreo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de Correo repetidos");
                $("#correo_persona").val("@gmail.com");
                $("#correo_persona").focus();
                //siguienteCampo("#direccion_persona", "#botonModificar", true);
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
function numero(e) {
    key = e.keyCode || e.which;

    teclado = String.fromCharCode(key).toLowerCase();


    letras = " 1234567890+-";

    especiales = "8-37-38-46-164";

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
function sololetras(e) {
    key = e.keyCode || e.which;

    teclado = String.fromCharCode(key).toLowerCase();


    letras = " abcdefghijklmnñopqrstvwxyzu/.";

    especiales = "8-37-38-46-164";

    teclado_especial = false;

    for (var i in especiales) {
        if (key == especiales[i]) {
            teclado_especial = true;
            break;
        }
    }
    if (letras.indexOf(teclado) == -1 && !teclado_especial) {
        // $("#mensajes").html("solo letras.");
        alert("solo letras");
        return false;
    }
}
function validarFormularioper() {
    var valor = true;


    if ($("#nombre_persona").val().trim() === "") {
        valor = false;
        document.getElementById("nombre").innerHTML = "No puede estar vacio";
        $("#nombre_persona").focus();
    }

    if ($("#direccion_persona").val().trim() === "") {
        valor = false;
        document.getElementById("direccion").innerHTML = "No puede estar vacio";
        $("#direccion_persona").focus();
    }

    if ($("#id_tipopersona").val().trim() === "0") {
        valor = false;
        document.getElementById("tipo").innerHTML = "seleccione el tipo de persona";
        $("#id_tipopersona").focus();
    }

    if ($("#id_ciudad").val().trim() === "") {
        valor = false;
       document.getElementById("ciudad").innerHTML = "seleccione una ciudad";
        $("#id_ciudad").focus();
    }

    if ($("#id_estadocivil").val().trim() === "") {
        valor = false;
        document.getElementById("estado").innerHTML = "seleccione un estado";
        $("#id_estadocivil").focus();
    }
     if ($("#id_tipopersona").val().trim() === "") {
        valor = false;
        document.getElementById("tipo").innerHTML = "seleccione el tipo de persona";
        $("#id_tipopersona").focus();
    }

    if ($("#id_ciudad").val().trim() === "0") {
        valor = false;
       document.getElementById("ciudad").innerHTML = "seleccione una ciudad";
        $("#id_ciudad").focus();
    }

    if ($("#id_estadocivil").val().trim() === "0") {
        valor = false;
        document.getElementById("estado").innerHTML = "seleccione un estado";
        $("#id_estadocivil").focus();
    }

    if ($("#ruc_persona").val().trim() === "") {
        valor = false;
       document.getElementById("ruc").innerHTML = "No puede estar vacio";
        $("#ruc_persona").focus();
    }

    if ($("#telefono_persona").val().trim() === "") {
        valor = false;
       document.getElementById("telefono").innerHTML = "No puede estar vacio";
        $("#telefono_persona").focus();
    }

    if ($("#apellido_persona").val().trim() === "") {
        valor = false;

        document.getElementById("apellido").innerHTML = "No puede estar vacio";
        // $("#mensajes").html("Apellido no puede estar vacio.");
        $("#apellido_persona").focus();
    }

    return valor;
}

function limpiarFormulario() {
    $("#id_persona").val("0");
    $("#nombre_persona").val("");
    $("#direccion_persona").val("");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#id_tipopersona").val("0");
    $("#nombre_tipopersona").val("");
    $("#id_estadocivil").val("0");
    $("#nombre_estadocivil").val("");
    $("#telefono_persona").val("");
    $("#correo_persona").val("@gmail.com");
    $("#apellido_persona").val("");
    $("#ruc_persona").val("");

}

