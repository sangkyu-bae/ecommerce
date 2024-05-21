import React from 'react';
import {Editor} from "@toast-ui/react-editor";
import '@toast-ui/editor/dist/toastui-editor.css';
import Validation from "@/utils/validation/Validation";

interface InfoProps {
    content: string;
    editorRef: React.MutableRefObject<any>;
    onChangeDescription: () => void;
}

function ReactEdit({content, editorRef, onChangeDescription}: InfoProps) {
    const validation = Validation;
    return (
        <>
            {
                editorRef && (
                    <Editor
                        initialValue={content || ' '}
                        ref={editorRef}
                        placeholder="등록할 상품 내용을 입력해주세요."
                        previewStyle="vertical"
                        height="400px"
                        initialEditType="wysiwyg"
                        toolbarItems={[
                            ['heading', 'bold', 'italic', 'strike'],
                            ['hr', 'quote'],
                            ['ul', 'ol', 'task', 'indent', 'outdent'],
                            ['table', 'image', 'link'],
                            ['code', 'codeblock']
                        ]}
                        name="description"
                        id="description"
                        onChange={onChangeDescription}
                    ></Editor>
                )
            }
        </>
    );
}

export default ReactEdit;
