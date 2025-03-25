<template>
    <button @click="onClick" class="btn btn-sm btn-outline-success evaluation-btn">
        <i class="bi bi-graph-up me-1"></i>Ver Evaluación
    </button>
</template>

<script>
export default {
    name: 'EvaluationButton',
    props: {
        evaluationId: {
            type: [Number, String],
            required: true
        },
        reserva: {
            type: Object,
            required: true
        }
    },
    setup(props, { emit }) {
        const onClick = () => {
            emit('view-evaluation', props.reserva);
        };

        return {
            onClick
        };
    }
};
</script>
