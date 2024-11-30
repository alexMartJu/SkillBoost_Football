<template>
<div class="d-flex justify-content-center mt-4">
    <paginate :page="page" :page-count="totalPages" :page-range="2" :margin-pages="2" :click-handler="handlePageClick" :prev-text="'Prev'" :next-text="'Next'" :container-class="'pagination'" :page-class="'page-item'">
    </paginate>
</div>
</template>

  
<script>
import Paginate from 'vuejs-paginate-next';

export default {
    components: {
        Paginate
    },
    props: {
        page: Number,
        totalPages: Number
    },
    methods: {
        handlePageClick(pageNum) {
            this.$emit('update:page', pageNum);
            this.$emit('page-click', pageNum);
        }
    }
};
</script>
