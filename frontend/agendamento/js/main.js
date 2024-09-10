function initMap() {
    // Coordenadas para o centro do mapa (exemplo)
    var center = { lat: -23.5505, lng: -46.6333 };
    // Opções do mapa
    var mapOptions = {
      zoom: 12,
      center: center
    };
    // Inicialização do mapa
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
    // Marcador para um local de doação (exemplo)
    var marker = new google.maps.Marker({
      position: center,
      map: map,
      title: 'Local de Doação de Sangue'
    });
  }
  
  // Função para carregar o mapa após o carregamento da página
  window.onload = function() {
    initMap();
  };
  
(function ($) {
    "use strict";


     /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    $(document).ready(function () {

        $("#confirm-schedule").click(function () {
            var hospitalId = $("#hospital-select").val();
            var appointmentDate = $("input[name='appointment_date']").val();
    
            // Validação dos campos
            if (!hospitalId || !appointmentDate) {
                alert("Por favor, selecione um hospital e uma data válida.");
                return;
            }
    
            var requestData = {
                idDoador: 1,
                idHemocentro: hospitalId,
                data: appointmentDate
            };
    
            $.ajax({
                url: 'http://localhost:8081/api/agendamento',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(requestData),
                success: function (response) {
                    alert("Agendamento realizado com sucesso!");
                },
                error: function (error) {
                    console.error("Erro ao realizar o agendamento", error);
                    alert("Falha ao realizar o agendamento. Tente novamente.");
                }
            });
        });


        $("#hospital-select").click(function () {
            if ($("#hospital-select option").length > 1) {
                return;
            }
    
            $.ajax({
                url: 'http://localhost:8081/api/hemocentro',
                type: 'GET',
                contentType: "application/json",
                success: function (response) {
                    var hospitals = response.content;

                    $("#hospital-select").empty();

                    $("#hospital-select").append('<option value="">Selecione um hospital</option>');

                    hospitals.forEach(function(hospital) {
                        $("#hospital-select").append(
                            '<option value="' + hospital.id + '">' + hospital.nome + ' - ' + hospital.endereco + '</option>'
                        );
                    });
                },
                error: function (error) {
                    console.error("Erro ao carregar os hospitais", error);
                    alert("Falha ao carregar a lista de hospitais.");
                }
            });
        });
    });

})(jQuery);