<template>
    <div>
        <div class="editor-container">
            <quill-editor  v-model="content" ref="newEditor" :options="editorOption" @change="onEditorChange($event)" >
            </quill-editor>
            
            <!-- <el-button class="editor-btn" type="primary" @click="submit">提交</el-button> -->
           
        </div>

        <photoShow ref="photo" @handleSure="handleSure" style="position:absolute;" ></photoShow>

    </div>
</template>

<script>
    import { quillEditor } from 'vue-quill-editor';
    import Quill from 'quill'
    export default {
        name: 'editor',
        data: function(){

            return {
                content:"",
                addImgRange:"",
                uniqueId:"",
                editorOption: {
                    placeholder: '请在此输入文章内容',

                    },
                addImgRange: '',//全局参数，每次添加图片时记录当前索引和长度

            }
        },
        components: {
            quillEditor
        },
        props:{
            value: {
                type: String,
                default: () => "",
            },
            id: {
                type: Number,
                default: () => 0,
            }
        },
        watch: {
            value: function (newValue) {
               this.content=newValue;
            }
        },
         mounted() {
            var vm =this
            var imgHandler = async function(image) {
            vm.addImgRange = vm.$refs.newEditor.quill.getSelection()
                if (image) {
                    var fileInput = document.getElementById(vm.uniqueId) //隐藏的file文本ID
                    vm.opendig();
                }
            }
            vm.$refs.newEditor.quill.getModule("toolbar").addHandler("image", imgHandler);
        },
        created(){
            this.getcontent();
        },
        methods: {
            handleSure(photoData){
                var vm=this;
                if(!photoData){
                    return this.$message({
                    message: '请选择你要选择的图片',
                    type: 'warning'
                    });
                }
                var value=photoData.url;
                let index = vm.addImgRange != null?vm.addImgRange.index:0 ;// 获取插入时的位置索引，如果获取失败，则插入到最前面
                vm.$refs.newEditor.quill.insertEmbed(index , 'image', value, Quill.sources.USER);
                document.getElementById(vm.uniqueId).value='';
            },
            opendig(){
                this.$refs.photo.dialogVisible=true;
            },
            getcontent(){
                this.content=this.value;
            },
            onEditorChange({ editor, html, text }) {
                this.content = html;
                this.$emit("onEditorChange",this.content);
                this.$emit("onEditorChange2",this.content,this.id);
            },
            submit(){
                this.$message.success('提交成功！');
                this.$emit("submit",this.content);
            },
            imgClick(){
            }
        }
    }
</script>
<style scoped>
    .editor-btn{
        margin-top: 20px;
    }
    .editor-container{
        padding-top: 10px;
        border: 0px solid #ddd;
        max-width: 1000px;
    }
    .showDialog .el-dialog__wrapper {
        position: relative !important;
    }
</style>
<style>
    .ql-container.ql-snow{
        min-height: 300px!important;
    }
    .ql-container strong{
        font-weight: bold;
    }
    .ql-container em{
        font-style: italic;
    }
    .aaa{
        position:absolute;
        top: -110px;
        background-color: brown;
    }
</style>