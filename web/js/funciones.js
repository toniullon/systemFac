/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function siguienteCampo(actual, siguiente, preventDefault) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            if (preventDefault) {
                event.preventDefault();
            }
            $(siguiente).focus();
            $(siguiente).select();
        }
    });
}
function enterCampo(actual, ejecutar) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            eval(ejecutar);
        }
    });
}

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz.";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        alert("ingrese solo letras");
        //$("#codigo_producto").val('');
        return false;
    }
}

function SoloNumeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {

        alert("Solo ingrese numeros");
        //$("#codigo_articulo").val('');
        return false;
    }
}

function validarCorreo(elemento) {

    var texto = document.getElementById(elemento.id).value;
    var regex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;

    if (!regex.test(texto)) {
        document.getElementById("resultado").innerHTML = "Correo invalido";
    } else {
        document.getElementById("resultado").innerHTML = "";
    }

}

function validarAcceso() {
    $("#mensajes").html("Mensajes del Sistema");
    if ($("#usuario_usuario").val() === "") {
        document.getElementById("resultadoC").innerHTML = " Ingrese un usuario";
        $("#usuario_usuario").focus();
        // $("#mensajes").html("Usuario no debe estar vacio.");
        setTimeout(' location.reload()', 1500);
    } else if ($("#clave_usuario").val() === "") {
        document.getElementById("resultadoU").innerHTML = " Ingrese la clave";
        $("#clave_usuario").focus();
        // $("#mensajes").html("Clave no debe estar vacio.");
        setTimeout(' location.reload()', 15000);
    } else {
        validarAccesoAjax();
    }
}
function abrir() {
    document.getElementById("venta").style.display = "block";
    setTimeout(' location.reload()', 1500);
}
function validarAccesoAjax() {
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/validarAcceso.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.acceso === "true") {
                location.href = "menu.html";
            } else {
                $("#mensajes").html("Credencial Inválida.");
                abrir();
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo conectar con el servidor.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function verificarSesion(programa) {
    var url = 'jsp/verificarSesion.jsp';
    if (programa) {
        url = '../../../jsp/verificarSesion.jsp';
    }
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: url,
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.activo === "false") {
                if (programa) {
                    location.href = "../../../index.html";
                } else {
                    location.href = "index.html";
                }
            }
            $("#snombre_empresa").html("El Cordillerano");
            $("#slogin_usuario").html(json.login_usuario);
            //$("#snombre_usuario").html(json.nombre_usuario);
            $("#sid_usuario").val(json.id_usuario);
            $("#snombre_usuario").val(json.nombre_usuario);
            $("#sid_usuarios").val(json.id_usuario);
            $("#snombre_usuarios").val(json.nombre_usuario);
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("ERROR: No se pudo recuperar la sesión.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function cerrarSesion() {
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/cerrarSesion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html("Sesión Cerrada.");
        },
        error: function (e) {
            $("#mensajes").html("No se pudo cerrar la sesión.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

// GENERAR MENU //
function generarMenuPrincipal() {
    var datosFormulario = "";
    $.ajax({
        type: 'POST',
        url: 'jsp/generarMenuPrincipal.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#menuPrincipal").html(json.menu_principal);
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo generar el Menú Principal.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

// jQuery(document).ready(function () {
function cargarPos() {
    //obtenemos los valores en caso de tenerlos en un formulario ya guardado en la base de datos
    lat = $('#lati').val();
    lng = $('#long').val();
    //Asignamos al evento click del boton la funcion codeAddress

    //Inicializamos la función de google maps una vez el DOM este cargado
    initialize();
    //codeAddress();

}

function initialize() {

    geocoder = new google.maps.Geocoder();

    //Si hay valores creamos un objeto Latlng
    if (lat !== "" && lng !== "")
    {
        var latLng = new google.maps.LatLng(lat, lng);
    } else
    {
        var latLng = new google.maps.LatLng(-23.442503, -58.443831999999986);
    }
    //Definimos algunas opciones del mapa a crear
    var myOptions = {
        center: latLng, //centro del mapa
        zoom: 16, //zoom del mapa
        mapTypeId: google.maps.MapTypeId.ROADMAP //tipo de mapa, carretera, híbrido,etc
                //mapTypeId: google.maps.MapTypeId.HYBRID
    };
    //creamos el mapa con las opciones anteriores y le pasamos el elemento div
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

    //creamos el marcador en el mapa
    marker = new google.maps.Marker({
        map: map, //el mapa creado en el paso anterior
        position: latLng, //objeto con latitud y longitud
        draggable: true, //que el marcador se pueda arrastrar
        animation: google.maps.Animation.DROP,
    });
    //función que actualiza los input del formulario con las nuevas latitudes
    //Estos campos suelen ser hidden
    updatePosition(latLng);


}

//funcion que traduce la direccion en coordenadas
function codeAddress() {
    var address = "";
    //obtengo la direccion del formulario
    //var address = document.getElementById("direccion").value;
    //var address = "Asuncion Paraguay";
    if ($("#nombre_ciudad").val() === " ") {
        address = "Asuncion Paraguay";

    } else {
        address = $("#nombre_ciudad").val() + " " + "Paraguay";
    }
    //hago la llamada al geodecoder
    geocoder.geocode({'address': address}, function (results, status) {

        //si el estado de la llamado es OK
        if (status == google.maps.GeocoderStatus.OK) {
            //centro el mapa en las coordenadas obtenidas
            map.setCenter(results[0].geometry.location);
            //coloco el marcador en dichas coordenadas
            marker.setPosition(results[0].geometry.location);
            //actualizo el formulario      
            updatePosition(results[0].geometry.location);

            //Añado un listener para cuando el markador se termine de arrastrar
            //actualize el formulario con las nuevas coordenadas
            google.maps.event.addListener(marker, 'dragend', function () {
                updatePosition(marker.getPosition());
            });
        } else {
            //si no es OK devuelvo error
            alert("No podemos encontrar la direcci&oacute;n, error: " + status);
        }
    });
}


//funcion que simplemente actualiza los campos del formulario
function updatePosition(latLng)

{


    jQuery('#lati').val(latLng.lat());
    jQuery('#long').val(latLng.lng());

}


function dar_formato_numero(numero, separador_decimal, separador_miles) {
    var fnumero = "";
    var snumero = numero.toString().replace(/\./g, "");
    snumero = snumero.replace(/[a-z]|_|%/ig, "");
    var pdecimal = snumero.indexOf(",");
    var psigno = snumero.indexOf("-");
    var enumero = snumero;
    var edecimal = "";
    var esigno = "";
    if (psigno !== -1) {
        esigno = "-";
        enumero = snumero.substr(1, snumero.length);
    }
    if (pdecimal !== -1) {
        if (psigno === -1) {
            enumero = snumero.substr(0, pdecimal);
        } else {
            enumero = snumero.substr(1, pdecimal - 1);
        }
        edecimal = snumero.substr(pdecimal, snumero.length);
        console.log("--> " + enumero);
    }
    var longitud = enumero.length;
    for (pos = longitud - 1; pos >= 0; pos--) {
        var cnumero = enumero.charAt(pos);
        fnumero = cnumero + fnumero;
        if ((longitud - pos) !== longitud) {
            if ((longitud - pos) % 3 === 0) {
                fnumero = separador_miles + fnumero;
            }
        }
    }
    fnumero = esigno + fnumero + edecimal;
    return fnumero;
}

function dar_formato_hora(hora) {
    var fhora = "";
    var shora = hora.toString().replace(/\:/g, "");
    var longitud = shora.length - 1;
    if (longitud > 3) {
        longitud = 3;
    }
    for (pos = longitud; pos >= 0; pos--) {
        var chora = shora.charAt(pos);
        fhora = chora + fhora;
        if (pos === 2) {
            fhora = ":" + fhora;
        }
    }
    return fhora;
}

function formatearNumero(id) {
    var tecla = event.which;
    if (tecla !== 37 && tecla !== 38 && tecla !== 39 && tecla !== 40 && tecla !== 9) {
        var monto = $(id).val();
        $(id).val(dar_formato_numero(monto, ",", "."));
    }
}

function format(input)
{
    var num = input.value.replace(/\./g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        input.value = num;
    } else {
        alert('Solo se permiten numeros');
        input.value = input.value.replace(/[^\d\.]*/g, '');
    }
}



















function buscarNombrePedidoFecha() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreFecha.jsp',
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
            $("#botonconf").show();
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

function buscarNombrePedidoCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert(datosFormulario);
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
function buscarNombrePedido() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreP.jsp',
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

function fecha() {
    var hoy = new Date().toJSON().slice(0, 10);
    console.log(hoy);
    $("#bnombre_pedidocliente").val(hoy);
}
function buscarNombreVenta() {
     var datosFormulario = $("#formBuscar").serialize();
   // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreFac.jsp',
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
           $("#botonconf").show();
          
       
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
function buscarNombreV() {
     var datosFormulario = $("#formBuscar").serialize();
   // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreV.jsp',
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
function buscarNombrePedidoProveedor() {
    var datosFormulario = $("#formBuscar").serialize();
    //  alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePedidoc.jsp',
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
            $("#botonconf").show();
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
function buscarNombreCompra() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCo.jsp',
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
           $("#botonconf").show();
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
function buscarNombreCompras() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreC.jsp',
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