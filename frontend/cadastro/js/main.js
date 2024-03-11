
(function ($) {
    "use strict";

    var input = $('.validate-input .input100');

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
    
    $('input[name="jaDoou"]').change(function() {
        if ($('input[name="jaDoou"]:checked').val() === 'yes') {
            $('#lastDonationDateField').show();
        } else {
            $('#lastDonationDateField').hide();
        }
    });
    
    /*========================== AJAX REGISTER USER ==========================*/
    $('#registrationForm').on('submit', function(event) {
        var check = true;

        // Check if the user has not donated before
        var hasDonated = $('input[name="jaDoou"]:checked').val() === 'yes';
    
        input.each(function(){
            // Skip validation for lastDonationDateField if hasDonated is false
            if (!hasDonated && $(this).attr('name') === 'lastdonationdate') {
                return true; // Equivalent to 'continue' in a regular loop
            }
            if(validate(this) == false){
                showValidate(this);
                check = false; // If any validation fails, set check to false
            }
        });

        // Prevent form submission by default if validation fails
        if(!check) {
            console.log("Form check didn't pass")
            event.preventDefault();
            return; // Stop execution if the form is not valid
        }
        
        // If the code reaches here, it means validation passed
        event.preventDefault(); // Still prevent the default submit

        var registerData = {
            username: $('input[name="email"]').val(),
            password: $('input[name="senha"]').val(),
            nome: $('input[name="fullname"]').val(),
            cpf: $('input[name="cpf"]').val(),
            dataNascimento: $('input[name="birthdate"]').val() + "T00:00:00.000",
            sexo: $('input[name="gender"]:checked').val() === 'male' ? 'M' : 'F',
            cep: $('input[name="cep"]').val(),
            telefone: $('input[name="phone"]').val(),
            fichaMedica: {
                doencasPreexistentes: $('input[name="preexistingconditions"]').val(),
                peso: parseFloat($('input[name="weight"]').val()),
                possuiTatuagens:  $('input[name="tattoo"]:checked').val() === 'yes',
                tipoSanguineo: $('input[name="bloodtype"]').val(),
                //jaDoou: $('input[name="jaDoou"]:checked').val() === 'yes',
                ultimaDoacao: $('input[name="jaDoou"]:checked').val() === 'yes' ? $('input[name="lastdonationdate"]').val() + "T09:30:00.000" : null            },
        };

        //Register the user
        $.ajax({
            url: 'http://localhost:8081/api/user/register',
            type: 'POST',
            data: JSON.stringify(registerData),
            contentType: "application/json",
            success: function(response) {
                console.log("User registered");
                // log in
                loginUser(response.idUsuario, registerData);
            },
            error: function(error) {
                console.error("Registration failed", error);
            }
        });
    });

    function loginUser(idUsuario, registerData) {
        $.ajax({
            url: 'http://localhost:8081/api/user/login',
            type: 'POST',
            data: JSON.stringify({ username: registerData.username, password: registerData.password }),
            contentType: "application/json",
            success: function(response) {
                console.log("Logged in successfully");
                var tokenJWT = response.tokenJWT;
                createDonor(tokenJWT, idUsuario, registerData);
            },
            error: function(error) {
                console.error("Login failed", error);
            }
        });
    }

    function createDonor(tokenJWT, idUsuario, registerData) {
        $.ajax({
            url: 'http://localhost:8081/api/doador',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                idUsuario: idUsuario,
                nome: registerData.nome,
                cpf: registerData.cpf,
                dataNascimento: registerData.dataNascimento,
                sexo: registerData.sexo,
                cep: registerData.cep,
                peso: registerData.peso,
                telefone: registerData.telefone,
                fichaMedica: registerData.fichaMedica
            }),
            headers: {
                "Authorization": "Bearer " + tokenJWT
            },
            success: function(response) {
                console.log("Donor created successfully", response);
            },
            error: function(error) {
                console.error("Failed to create donor", error);
            }
        });
    }
})(jQuery);