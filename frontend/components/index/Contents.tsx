import { TypewriterEffect } from "@/components/ui/typewriter-effect";

export const Contents: React.FC = () => {
    const words = [
        {
            text: "여행",
        },
        {
            text: "계획을",
        },
        {
            text: "쉽게",
        },
        {
            text: "공유하는",
        },
        {
            text: "트래블",
            className: "text-blue-500 dark:text-blue-500",
        },
        {
            text: "로그.",
            className: "text-blue-500 dark:text-blue-500",
        },
    ];

    return (
        <div className="flex flex-col items-center justify-center mt-20">
            <p className="text-neutral-600 dark:text-neutral-200 text-base  mb-10">
                자유 여행을 떠나는 길
            </p>
            <TypewriterEffect words={words} className="text-xl" />
            <div className="flex flex-col sm:flex-row space-y-4 sm:space-y-0 space-x-0 sm:space-x-4 mt-10">
                <button className="w-40 h-10 rounded-xl bg-black border dark:border-white border-transparent text-white text-sm">
                    여행 로그 작성
                </button>
                <button className="w-40 h-10 rounded-xl bg-white text-black border border-black  text-sm">
                    데모 영상
                </button>
            </div>
        </div>
    );
};
