"use strict";(self["webpackChunksimida_web"]=self["webpackChunksimida_web"]||[]).push([[408],{7408:function(e,a,l){l.r(a),l.d(a,{default:function(){return S}});l(8858),l(1318),l(3228);var n=l(3396),t=l(4870),o=l(7625),s=l(5878),r=l(7178),i=l(9143),u=l(4471),d=l(4476);const p=e=>((0,n.dD)("data-v-afd75244"),e=e(),(0,n.Cn)(),e),c={class:"content"},m=p((()=>(0,n._)("div",{style:{fontSize:"24px",paddingTop:"20px",paddingBottom:"30px",display:"flex",justifyContent:"center"}}," 账号信息设置 ",-1))),f={style:{display:"flex",justifyContent:"center"}},v=["src"],g=p((()=>(0,n._)("span",{class:"msg-span"},"用户名",-1))),h=p((()=>(0,n._)("div",{style:{fontSize:"13px",color:"#c2c2c2",marginTop:"-20px",marginLeft:"100px",marginBottom:"20px"}}," 不可修改 ",-1))),_=p((()=>(0,n._)("span",{class:"msg-span"},"邮箱",-1))),x=p((()=>(0,n._)("div",{style:{fontSize:"13px",color:"#c2c2c2",marginTop:"-20px",marginLeft:"100px",marginBottom:"20px"}}," 不可修改 ",-1))),w=p((()=>(0,n._)("span",{class:"msg-span"},"昵称",-1))),y=p((()=>(0,n._)("div",{style:{fontSize:"13px",color:"#c2c2c2",marginTop:"-20px",marginLeft:"100px",marginBottom:"20px"}}," 请输入3~30个字符，支持中英文、数字 ",-1))),k=p((()=>(0,n._)("span",{class:"msg-span"},"简介",-1))),I=p((()=>(0,n._)("div",{style:{fontSize:"13px",color:"#c2c2c2",marginTop:"-20px",marginLeft:"100px",marginBottom:"60px"}}," 请输入1~60个字符 ",-1))),b={class:"btn-box"};var z={__name:"editNormalMsg",setup(e){(0,n.bv)((()=>{N.value=o.h.userInfo.avatar,W.value.avatar=o.h.userInfo.avatar,W.value.nickName=o.h.userInfo.nickName,W.value.description=o.h.userInfo.description}));const a=(0,t.iH)(""),l=(0,t.iH)(null),p=(0,t.iH)(!0),z=(0,t.iH)(!1),W=(0,t.iH)({nickName:"",description:"",avatar:""}),N=(0,t.iH)(""),S=(e,a)=>{N.value=URL.createObjectURL(e.raw)},U=()=>{z.value=!0,!0===p.value&&N.value!==o.h.userInfo.avatar?(p.value=!1,H()):C()},C=()=>{u.Z.put("/user",{nickName:W.value.nickName,description:W.value.description,avatar:W.value.avatar},{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((e=>{4001===e.data.code&&(V("success",e.data.msg),o.h.userInfo=e.data.data,(0,s.D$)("token",e.data.path,5),u.Z.get("/user/").then((e=>{2001===e.data.code&&((0,s.D$)("userInfo",e.data.data,5),o.h.userInfo=e.data.data.userInfo,o.h.followers=e.data.data.followers,o.h.followings=e.data.data.followings,o.h.isLogin=!0)})))}))};function H(){return new Promise(((e,a)=>{T.value.submit(),e()}))}const L=function(e){return a.value=(0,s.le)("token"),"image/jpeg"!==e.type&&"image/png"!==e.type&&"image/gif"!==e.type?(r.z8.error("头像只允许JPG、PNG或GIF格式!"),!1):!(e.size/1024/1024>10)||(r.z8.error("上传的头像文件大小不能 10MB!"),!1)},B=()=>{(0,r.z8)({type:"error",message:"上传失败"})},R=async function(e,a){1001===e.code?((0,r.z8)({type:"success",message:"上传成功"}),W.value.avatar=e.data.filePath,C()):(0,r.z8)({type:"warning",message:e.msg})},T=(0,t.iH)(),V=(e,a,l)=>{d.Z[e]({message:a,description:l,duration:2})};return(e,s)=>{const r=(0,n.up)("el-upload"),u=(0,n.up)("el-form-item"),d=(0,n.up)("el-input"),p=(0,n.up)("a-button");return(0,n.wg)(),(0,n.iD)(n.HY,null,[(0,n._)("div",c,[m,(0,n.Wm)((0,t.SU)(i.ly),{ref_key:"updateFormRef",ref:l,model:W.value},{default:(0,n.w5)((()=>[(0,n._)("div",f,[(0,n.Wm)(u,null,{default:(0,n.w5)((()=>[(0,n.Wm)(r,{ref_key:"uploadRef",ref:T,class:"avatar-uploader",action:"/upload",data:{operationCode:1},headers:{Authorization:a.value},"show-file-list":!1,"on-success":R,"before-upload":L,"on-error":B,"auto-upload":!1,"on-change":S},{default:(0,n.w5)((()=>[(0,n._)("img",{class:"change-avatar",src:N.value},null,8,v)])),_:1},8,["headers"])])),_:1})]),(0,n.Wm)(u,null,{default:(0,n.w5)((()=>[g,(0,n._)("span",null,[(0,n.Wm)(d,{disabled:!0,placeholder:(0,t.SU)(o.h).userInfo.userName,class:"el-input-con"},null,8,["placeholder"])]),h])),_:1}),(0,n.Wm)(u,null,{default:(0,n.w5)((()=>[_,(0,n._)("span",null,[(0,n.Wm)(d,{disabled:!0,placeholder:(0,t.SU)(o.h).userInfo.email,class:"el-input-con"},null,8,["placeholder"])]),x])),_:1}),(0,n.Wm)(u,null,{default:(0,n.w5)((()=>[w,(0,n._)("span",null,[(0,n.Wm)(d,{class:"el-input-con",modelValue:W.value.nickName,"onUpdate:modelValue":s[0]||(s[0]=e=>W.value.nickName=e),minlength:"3",maxlength:"30",placeholder:(0,t.SU)(o.h).userInfo.nickName},null,8,["modelValue","placeholder"])]),y])),_:1}),(0,n.Wm)(u,null,{default:(0,n.w5)((()=>[k,(0,n._)("span",null,[(0,n.Wm)(d,{type:"textarea",autosize:{minRows:2,maxRows:7},modelValue:W.value.description,"onUpdate:modelValue":s[1]||(s[1]=e=>W.value.description=e),maxlength:"60",placeholder:(0,t.SU)(o.h).userInfo.description,class:"el-input-con"},null,8,["modelValue","placeholder"])]),I])),_:1})])),_:1},8,["model"])]),(0,n._)("div",b,[(0,n.Wm)(p,{type:"primary",class:"save-btn",onClick:s[2]||(s[2]=e=>U())},{default:(0,n.w5)((()=>[(0,n.Uk)("保存")])),_:1})])],64)}}},W=l(89);const N=(0,W.Z)(z,[["__scopeId","data-v-afd75244"]]);var S=N}}]);
//# sourceMappingURL=408.636f4e16.js.map