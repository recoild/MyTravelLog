import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function dashboard() {
    return (
        <div>
            <h1>Dashboard</h1>
            <p>Welcome</p>
            <Link href="/">
                <Button>Go back</Button>
            </Link>
        </div>
    );
}
