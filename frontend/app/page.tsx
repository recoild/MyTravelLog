import Header from "@/components/index/Header";
import { Contents } from "@/components/index/Contents";
import GridPattern from "@/components/ui/grid-pattern";
import { cn } from "@/lib/utils";
import { cookies } from "next/headers";

async function fetchSessionInfo(sessionValue: string) {
    console.log("SESSION", sessionValue);

    const res = await fetch("http://localhost:1000/springbootserver/session", {
        method: "GET",
        credentials: "include",
        headers: {
            Cookie: `SESSION=${sessionValue}`,
        },
        cache: "no-store",
    });

    if (!res.ok) {
        throw new Error("Failed to fetch session info");
    }

    const data = await res.json();
    return data;
}

export default async function Home() {
    // return (
    //     <>
    //         <p>hello</p>
    //     </>
    // );

    const cookieStore = cookies();
    let sessionInfo = "";
    try {
        const SESSION = cookieStore.get("SESSION");
        if (!SESSION) {
            throw new Error("No session cookie found");
        }
        sessionInfo = await fetchSessionInfo(SESSION?.value as string);
    } catch (error) {
        console.error("Failed to fetch session info", error);
    }

    return (
        <main className="overflow-hidden h-screen">
            <GridPattern
                className={cn(
                    "[mask-image:radial-gradient(1000px_circle_at_center,white,transparent)]",
                    "inset-x-0  ",
                    "z-[-1]"
                )}
            />
            <div className=" ">
                <Header />
                <Contents />
            </div>
        </main>
    );
}
