$.when($.ready).then(function () {

    function savePost(data) {
        $.ajax({
            contentType: "application/json",
            dataType: "json",
            type: "PUT",
            url: "/api/posts/",
            data: data
    }).done(function (msg) {
            alert("Updated succesfully");
            location.href="/posts/read/"+ msg;
        });
    }

    function executeSave(e) {
        e.preventDefault();
        var formData = formHelper($('#formPost').serializeArray());

        savePost(formData);
    }

    $("#save").click(executeSave);

    function formHelper(unindexed_array) {

        var indexed_array = {};

        $.map(unindexed_array, function (n, i) {
            indexed_array[n['name']] = n['value'];
        });

        return JSON.stringify(indexed_array);
    }
});