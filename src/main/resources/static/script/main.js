
function menu_click(ths,id){
     let buttonValue = $(ths).text().trim();
     console.log(buttonValue, id);

     if(id==='admin'){
     console.log("관리자");
         $.ajax({
                    type: 'GET',
                    url: '/admin-main-menu',
                    data: {buttonValue: buttonValue},
                    success: function (data) {
                        $('#ajaxSection').html(data);
                    },
                    error: function (xhr, status, error) {
                        console.error("에러 발생:", error);
                    }
                });
     }else{
     console.log(buttonValue);
        $.ajax({
                    type: 'GET',
                    url: '/center-main-menu',
                    data: {buttonValue: buttonValue},
                    success: function (data) {
                        $('#ajaxSection').html(data);
                    },
                    error: function (xhr, status, error) {
                        console.error("에러 발생:", error);
                    }
                });
     }

}

        /* 전화번호 자동 하이픈 */
        const autoHyphen = (target) => {
          target.value = target.value
            .replace(/[^0-9]/g, '')
            .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
        }


//admin js 시작
//function reset(id){
//
//   console.log(page);
//   console.log(id);
//   if(id==='admin'){
//      if(page==='admin/admin_center_enroll'){
//            window.reload();
//      }else if(page==='admin/admin_center_manage'){
//            window.reload();
//      }else if(page==='admin/admin_center_detail'){
//
//      }else if(page==='admin/admin_center_modify'){
//
//      }
//   }else if(id!=='admin'){
//
//   }
//}

function updateCenterGET(centercode){
    $.ajax({
                        type: 'GET',
                        url: '/update-center',
                        data: {centercode : centercode},
                        success: function (data) {
                            $('#ajaxSection').html(data);
                        },
                        error: function (xhr, status, error) {
                            console.error("에러 발생:", error);
                        }
                    });
}

//admin js 끝

