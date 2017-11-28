<template>
  <div class="hello">
    <h1>UploadForm</h1>
    <form id="uploadForm" ref="uploadForm" onsubmit="return false">
      <div>
        <label for="bucket">Bucket:</label>
        <input id="bucket" type="text" />
      </div>
      <div>
        <label for="accessKey">Access Key:</label>
        <input id="accessKey" type="text" />
      </div>
      <div>
        <label for="secretKey">Secret Key:</label>
        <input id="secretKey" type="text" />
      </div>
      <div>
        <label for="targetPath">Target path:</label>
        <input id="targetPath" type="text" />
      </div>
      <div>
        <label for="file">File:</label>
        <input id="file" type="file" />
      </div>
      <input type="submit" value="Submit" />
    </form>
    <div v-if="url">
      Uploaded url:
      <a v-bind:href="url">{{url}}</a>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'UploadForm',
    data() {
      return {
        url: undefined,
      };
    },
    mounted() {
      this.$refs.uploadForm.addEventListener('submit', () => {
        const formData = new FormData();
        formData.append('bucket', document.getElementById('bucket').value);
        formData.append('accessKey', document.getElementById('accessKey').value);
        formData.append('secretKey', document.getElementById('secretKey').value);
        formData.append('targetPath', document.getElementById('targetPath').value);
        formData.append('file', document.getElementById('file').files[0]);
  
        fetch('http://localhost:8081/files', {
          method: 'POST',
          body: formData,
        })
        .then(resp => resp.json())
        .then((response) => {
          this.url = response.location;
        });
      });
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    display: inline-block;
    margin: 0 10px;
  }
  
  a {
    color: #42b983;
  }
</style>
