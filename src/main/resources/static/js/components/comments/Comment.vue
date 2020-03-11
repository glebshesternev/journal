<template>
    <div style="position: relative ; left: 60px;">
        {{ comment }}
    </div>
</template>

<script>
    export default {
        name: "Comment",
        props: ['comment'],
        data() {
            return {
                id: ''
            }
        }
    }
</script>

<style scoped>

</style>