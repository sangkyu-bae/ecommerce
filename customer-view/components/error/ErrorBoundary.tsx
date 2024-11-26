// import React from 'react';
//
// export class ErrorBoundary extends React.Component{
//
//     constructor(props) {
//         super(props);
//         this.state = { hasError: false };
//     }
//
//     static getDerivedStateFromError(error) {
//         // 다음 렌더링에서 폴백 UI가 보이도록 Error상태 업데이트.
//         return { hasError: true };
//     }
//
//     componentDidCatch(error, errorInfo) {
//         // 특정 서비스에 에러를 기록할 수도 있다.
//         // logErrorToMyService(error, errorInfo);
//         console.log("error")
//     }
//
//     render() {
//         if (this.state.hasError) {
//             // 폴백 UI를 커스텀하여 렌더링할 수 있다.
//             return <h1>Something went wrong.</h1>;
//         }
//
//         return this.props.children;
//     }
//
// }

import React from "react";

type ErrorBoundaryState = {
    hasError: boolean;
};

export class ErrorBoundary extends React.Component<{}, ErrorBoundaryState> {
    constructor(props: {}) {
        super(props);
        this.state = { hasError: false };
    }

    static getDerivedStateFromError(): Partial<ErrorBoundaryState> {
        return { hasError: true }; // 에러 발생 시 상태 업데이트
    }

    componentDidCatch(error: Error, errorInfo: React.ErrorInfo): void {
        console.error("Error caught by ErrorBoundary:", error, errorInfo);
    }

    render() {
        if (this.state.hasError) {
            return <h1>Something went wrong. Please try again later.</h1>;
        }
        return this.props.children;
    }
}