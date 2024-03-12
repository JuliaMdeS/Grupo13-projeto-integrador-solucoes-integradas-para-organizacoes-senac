
(function ($) {
    "use strict";
    var jwtToken = sessionStorage.getItem('jwtToken');
    

    $.ajax({
        url: 'http://localhost:8081/api/doador',
        type: 'GET',
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + jwtToken);
        },
        success: function(response) {
            response.content.forEach(function(doador) {
                var cardDiv = $('<div>').addClass('donor-card');

                // Add Name as the card title
                cardDiv.append($('<h4>').text(doador.nome));

                // Add other donor details one by one
                cardDiv.append(createDetailDiv('CPF: ', doador.cpf));
                cardDiv.append(createDetailDiv('Telefone: ', doador.telefone));
                cardDiv.append(createDetailDiv('Data de Nascimento: ', new Date(doador.dataNascimento).toLocaleDateString()));
                cardDiv.append(createDetailDiv('Sexo: ', doador.sexo === 'M' ? 'Masculino' : 'Feminino'));
                
                // Ficha Medica
                var fichaMedica = doador.fichaMedica;
                cardDiv.append(createDetailDiv('Peso: ', fichaMedica.peso));
                cardDiv.append(createDetailDiv('Tipo Sanguíneo: ', fichaMedica.tipoSanguineo));
                cardDiv.append(createDetailDiv('Possui Tatuagens: ', fichaMedica.possuiTatuagens ? 'Sim' : 'Não'));
                cardDiv.append(createDetailDiv('Doenças Preexistentes: ', fichaMedica.doencasPreexistentes));

                // Append the card to the container
                $('#donorsContainer').append(cardDiv);
            });
        },
        error: function(error) {
            console.error("Failed to fetch donor information", error);
        }
    });
    function createDetailDiv(label, value) {
        var detailDiv = $('<div>').addClass('donor-detail');
        detailDiv.append($('<span>').addClass('donor-detail-label').text(label));
        detailDiv.append($('<span>').addClass('donor-detail-value').text(value || 'N/A')); // Handle null or undefined
        return detailDiv;
    }
})(jQuery);