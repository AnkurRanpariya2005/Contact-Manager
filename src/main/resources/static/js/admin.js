      // image preview
      console.log("image preview");
      const image_file_input = document.getElementById("image_file_input");
      const upload_image_preview = document.getElementById("upload_image_preview");

      image_file_input.addEventListener("change", function () {
        const file = this.files[0];
        if (file) {
          const reader = new FileReader();
          reader.onload = function () {
            document.getElementById("upload_image_preview").setAttribute("src", reader.result);
          };
          reader.readAsDataURL(file);
        }
      });
      console.log("image preview");
