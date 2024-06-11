import Header from "@/components/index/Header";
import { Contents } from "@/components/index/Contents";
import GridPattern from "@/components/ui/grid-pattern";
import { cn } from "@/lib/utils";

export default function Home() {
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
