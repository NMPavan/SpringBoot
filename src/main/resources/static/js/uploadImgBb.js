function uploadFile() {
  var file = document.getElementById("fileOb");
  var form = new FormData();
  //doubt need to clarification
  form.append("image", file.files[0]);
  var inputs = {
    url: "https://api.imgbb.com/1/upload?key=cfd8f499840c9e1a5ee510541a2ae821",
    method: "POST",
    timeout: 0,
    processData: false,
    mimeType: "multipart/form-data",
    contentType: false,
    data: form,
  };

  $.ajax(inputs).done(function (response) {
    var job = JSON.parse(response);
    $("#photoLoc").val(job.data.url);
  });
}