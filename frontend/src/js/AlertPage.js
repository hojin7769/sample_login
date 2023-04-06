import { defineComponent, h } from 'vue'
import VuetifyDialog from '@/components/VuetifyDialog.vue'
export default defineComponent({
  name: 'AlertPage',
  components: {
    VuetifyDialog
  },
  props: {
    title: {
      type: String,
      default: '경고'
    },
    content: {
      type: String,
      default: 'test'
    },
    onOk: {
      type: Function,
      default: () => {}
    },
    onCancel: {
      type: Function,
      default: () => {}
    }
  },
  setup(props) {
    return () => {
      h(VuetifyDialog, {
        title: props.title,
        content: props.content,
        onOk: props.onOk,
        onCancel: props.onCancel
      })
    }
  }
})
