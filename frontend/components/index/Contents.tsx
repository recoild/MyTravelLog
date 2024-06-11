import { TypewriterEffect } from "@/components/ui/typewriter-effect";
import { Button } from "../ui/button";
import Link from "next/link";
import Image from "next/image";

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
            text: "데일리",
            className: "text-blue-500 dark:text-blue-500",
        },
        {
            text: "트래블",
            className: "text-blue-500 dark:text-blue-500",
        },
    ];

    return (
        <div className="flex flex-col items-center justify-center mt-20 ">
            {/* <p className="text-neutral-600 dark:text-neutral-200 text-base  mb-10">
                자유 여행을 떠나는 길
            </p> */}
            <span className="pointer-events-none mb-10 whitespace-pre-wrap bg-gradient-to-b from-[#ffd319] via-[#ff2975] to-[#8c1eff] bg-clip-text text-center text-4xl sm:text-7xl font-bold leading-none tracking-tighter text-transparent">
                자유 여행을 떠나는 길
            </span>
            <TypewriterEffect words={words} className="text-xl" />
            {/* <div className="flex flex-col sm:flex-row space-y-4 sm:space-y-0 space-x-0 sm:space-x-4 mt-10">
                <button className="w-40 h-10 rounded-xl bg-black border dark:border-white border-transparent text-white text-sm">
                    여행 로그 작성
                </button>
                <button className="w-40 h-10 rounded-xl bg-white text-black border border-black  text-sm">
                    데모 영상
                </button>
            </div> */}
            <div className="mt-10 sm:mt-20 flex flex-col w-[400px] items-stretch">
                <Link href="/springbootserver/oauth2/authorization/google">
                    <Button className="font-bold w-full mb-5 h-[50px] text-xl">
                        <Image
                            src="/icons/google.svg"
                            alt="구글"
                            width={20}
                            height={20}
                            className="mr-2"
                        ></Image>
                        Google 로그인
                    </Button>
                </Link>
                <Link href="/springbootserver/oauth2/authorization/google">
                    <Button className="font-bold  w-full mb-5 h-[50px] text-xl">
                        <Image
                            src="/icons/naver.png"
                            alt="naver"
                            width={20}
                            height={20}
                            className="mr-2"
                        ></Image>
                        Naver 로그인
                    </Button>
                </Link>
                <Link href="/">
                    <Button className="font-bold  w-full mb-5 h-[50px] text-xl">
                        데모 영상
                    </Button>
                </Link>
            </div>
        </div>
    );
};
