$(function () {
    'use strict';
    var ctxData = xqsight.utils.getServerPath("cms");

    var console = window.console || {
            log: function () {
            }
        };
    var URL = window.URL || window.webkitURL;
    var $image = $('#image');
    var $download = $('#download');
     /*var $dataX = $('#dataX');
     var $dataY = $('#dataY');
     var $dataHeight = $('#dataHeight');
     var $dataWidth = $('#dataWidth');
     var $dataRotate = $('#dataRotate');
     var $dataScaleX = $('#dataScaleX');
     var $dataScaleY = $('#dataScaleY');*/
    var options = {
        aspectRatio: 16 / 9,
        dragCrop:true,
        movable:true,
        resizable:true,
        preview: '.img-preview',
        checkCrossOrigin: false,
        cropBoxResizable : false
        /*crop: function (e) {
         $dataX.val(Math.round(e.x));
         $dataY.val(Math.round(e.y));
         $dataHeight.val(500);
         $dataWidth.val(1920);
         $dataRotate.val(e.rotate);
         $dataScaleX.val(e.scaleX);
         $dataScaleY.val(e.scaleY);
         }*/
    };
    var originalImageURL = $image.attr('src');
    var uploadedImageURL;

    var fileName;


    // Tooltip
    $('[data-toggle="tooltip"]').tooltip();


    // Cropper
    $image.on({
        ready: function (e) {
            console.log(e.type);
        },
        cropstart: function (e) {
            console.log(e.type, e.action);
        },
        cropmove: function (e) {
            console.log(e.type, e.action);
        },
        cropend: function (e) {
            console.log(e.type, e.action);
        },
        crop: function (e) {
            console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
        },
        zoom: function (e) {
            console.log(e.type, e.ratio);
        }
    }).cropper(options);


    // Buttons
    if (!$.isFunction(document.createElement('canvas').getContext)) {
        $('button[data-method="getCroppedCanvas"]').prop('disabled', true);
    }

    if (typeof document.createElement('cropper').style.transition === 'undefined') {
        $('button[data-method="rotate"]').prop('disabled', true);
        $('button[data-method="scale"]').prop('disabled', true);
    }


    // Download
    if (typeof $download[0].download === 'undefined') {
        $download.addClass('disabled');
    }


    // -------- 将以base64的图片url数据转换为Blob --------
    function base64ToBlob(base64, mime) {
        mime = mime || '';
        var sliceSize = 1024;
        var byteChars = window.atob(base64);
        var byteArrays = [];

        for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
            var slice = byteChars.slice(offset, offset + sliceSize);

            var byteNumbers = new Array(slice.length);
            for (var i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }

            var byteArray = new Uint8Array(byteNumbers);

            byteArrays.push(byteArray);
        }

        return new Blob(byteArrays, {type: mime});
    }


    function uploadImg(data) {
        var base64ImageContent = data.replace(/^data:image\/(png|jpg|jpeg);base64,/, "");
        var blob = base64ToBlob(base64ImageContent, 'image/png');
        var formData = new FormData();
        formData.append("uploadFile", blob,fileName);
        $.support.cors = true;
        var _progress_index = 0;
        $.ajax({
            type: "POST",
            cache: false,
            dataType: "text",
            data: formData,
            processData: false,
            contentType: false,
            url: ctxData + "/files/core/upload_image?date=" + new Date().getTime(),  //这里是网址
            timeout: 5000, //超时时间
            beforeSend : function () {
                _progress_index = xqsight.progress.show();
            },
            success: function (data) {
                xqsight.progress.hide(_progress_index);
                try {
                    var obj = JSON.parse(data);
                    if (obj.status == "0") {
                        window.parent._imgCallBack({"type":$.getUrlParam("type"),"url":obj.fileUrl});
                    }
                } catch (e) {
                    console.log(e.message);
                }
            },
            error: function (err) {
                xqsight.progress.hide(_progress_index);
                alert(err);
            }
        });
    }


    // Methods
    $('.docs-buttons').on('click', '[data-method]', function () {
        var $this = $(this);
        var data = $this.data();
        var $target;
        var result;

        if ($this.prop('disabled') || $this.hasClass('disabled')) {
            return;
        }

        if ($image.data('cropper') && data.method) {
            data = $.extend({}, data); // Clone a new one

            if (typeof data.target !== 'undefined') {
                $target = $(data.target);

                if (typeof data.option === 'undefined') {
                    try {
                        data.option = JSON.parse($target.val());
                    } catch (e) {
                        console.log(e.message);
                    }
                }
            }

            if (data.method === 'rotate') {
                $image.cropper('clear');
            }

            result = $image.cropper(data.method, data.option, data.secondOption);

            if (data.method === 'rotate') {
                $image.cropper('crop');
            }

            switch (data.method) {
                case 'scaleX':
                case 'scaleY':
                    $(this).data('option', -data.option);
                    break;
                case 'getCroppedCanvas':
                    if (result) {
                        var image = result.toDataURL('image/jpeg');
                        uploadImg(image);
                    }
                    break;
                case 'destroy':
                    if (uploadedImageURL) {
                        URL.revokeObjectURL(uploadedImageURL);
                        uploadedImageURL = '';
                        $image.attr('src', originalImageURL);
                    }
                    break;
            }

            if ($.isPlainObject(result) && $target) {
                try {
                    $target.val(JSON.stringify(result));
                } catch (e) {
                    console.log(e.message);
                }
            }

        }
    });


    // Keyboard
    $(document.body).on('keydown', function (e) {

        if (!$image.data('cropper') || this.scrollTop > 300) {
            return;
        }

        switch (e.which) {
            case 37:
                e.preventDefault();
                $image.cropper('move', -1, 0);
                break;

            case 38:
                e.preventDefault();
                $image.cropper('move', 0, -1);
                break;

            case 39:
                e.preventDefault();
                $image.cropper('move', 1, 0);
                break;

            case 40:
                e.preventDefault();
                $image.cropper('move', 0, 1);
                break;
        }

    });


    // Import image
    var $inputImage = $('#inputImage');

    if (URL) {
        $inputImage.change(function () {
            var files = this.files;
            var file;

            if (!$image.data('cropper')) {
                return;
            }

            if (files && files.length) {
                file = files[0];
                fileName = file.name;
                if (/^image\/\w+$/.test(file.type)) {
                    if (uploadedImageURL) {
                        URL.revokeObjectURL(uploadedImageURL);
                    }

                    uploadedImageURL = URL.createObjectURL(file);
                    $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
                    $inputImage.val('');
                } else {
                    window.alert('Please choose an image file.');
                }
            }
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }

});
