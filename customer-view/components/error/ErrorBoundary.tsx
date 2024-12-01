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